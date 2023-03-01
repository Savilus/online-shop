package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.pol122.auctionservice.model.CartItem;
import pl.sda.pol122.auctionservice.model.Product;
import pl.sda.pol122.auctionservice.services.CartService;
import pl.sda.pol122.auctionservice.services.ProductService;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping()
public class CartController {

    private final CartService cartService;

    private final ProductService productService;

    @GetMapping("/addToCart/{productId}")
    public String addToCart(Model model, @PathVariable String productId) {
        cartService.addProductToCart(productService.getProductById(Integer.valueOf(productId)));
        model.addAttribute("product", productService.getProductById(Integer.valueOf(productId)));
        return "redirect:/product/details/{productId}";
    }

    @GetMapping("/buyNow/{productId}")
    public String buyNow(Model model, @PathVariable String productId) {
        Product productById = productService.getProductById(Integer.valueOf(productId));
        cartService.addProductToCart(productById);
        model.addAttribute("product", productById);
        return "redirect:/cart";
    }

    @GetMapping("/cart/increase/{productId}")
    public String increaseQuantityOfProductsInCart(Model model, @PathVariable String productId){
        Product productById = productService.getProductById(Integer.valueOf(productId));
        cartService.increaseProductQuantityInCart(productById);
        model.addAttribute("product", productById);
        return "redirect:/cart";
    }

    @GetMapping("/cart/{productId}")
    public String decreaseQuantityOfProductsInCart(Model model, @PathVariable String productId) {
        cartService.decreaseProductQuantityInCart(productService.getProductById(Integer.valueOf(productId)));
        model.addAttribute("product", productService.getProductById(Integer.valueOf(productId)));
        return "redirect:/cart";
    }

    @GetMapping("/deleteFromCart/{productId}")
    public String deleteFromCart(@PathVariable String productId) {
        Product productById = productService.getProductById(Integer.valueOf(productId));
        CartItem cartItem = new CartItem(productById, 0);
        cartService.deleteProductFromCart(cartItem);
        return "redirect:/cart";
    }


    @GetMapping(path = "/cart/checkout")
    public String loadCartCheckout() {
        if (cartService.getAllProducts().isEmpty() && cartService.checkIfStockIsAvailable()) {
            return "redirect:/cart";
        } else {
            return "checkout";
        }
    }


    @PostMapping("/cart/checkout")
    public String submitUserPayment() {
        if(cartService.checkIfStockIsAvailable()){
            List<CartItem> orderedProducts = cartService.getAllProducts();
            cartService.submitPayment(orderedProducts);
            cartService.clearCart();
        }
        return "redirect:/cart";

    }

    @GetMapping(path = "/cart")
    public String loadUserCart(Model model) {
        List<CartItem> cartItems = cartService.getAllProducts();
        BigDecimal totalPriceOfCart = cartService.getTotalPriceOfCart();
        model.addAttribute("price", totalPriceOfCart);
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }
}

