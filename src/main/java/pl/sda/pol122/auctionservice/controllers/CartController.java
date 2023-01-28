package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.pol122.auctionservice.model.CartItem;
import pl.sda.pol122.auctionservice.model.Product;
import pl.sda.pol122.auctionservice.services.DefaultCartService;
import pl.sda.pol122.auctionservice.services.DefaultProductService;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping()
public class CartController {

    private final DefaultCartService defaultCartService;

    private final DefaultProductService defaultProductService;


    @PostMapping("/addToCart/{productId}")
    public String addToCart(Model model, @PathVariable String productId) {
        defaultCartService.addProductToCart(defaultProductService.getProductById(Integer.valueOf(productId)));
        model.addAttribute("product", defaultProductService.getProductById(Integer.valueOf(productId)));
        return "redirect:/product/details/{productId}";
    }

    @GetMapping("/buyNow/{productId}")
    public String buyNow(Model model, @PathVariable String productId) {
        defaultCartService.addProductToCart(defaultProductService.getProductById(Integer.valueOf(productId)));
        model.addAttribute("product", defaultProductService.getProductById(Integer.valueOf(productId)));
        return "redirect:/cart";
    }

    @DeleteMapping("/deleteFromCart/{productId}")
    public String deleteFromCart(Model model, @PathVariable String productId) {
        Product productById = defaultProductService.getProductById(Integer.valueOf(productId));
        CartItem cartItem = new CartItem(productById, 0);
        defaultCartService.deleteProductFromCart(cartItem);
        return "redirect:/cart";
    }


    @GetMapping(path = "/cart/checkout")
    public String loadCartCheckout() {
        return "checkout";
    }

    @GetMapping(path = "/cart")
    public String loadUserCart(Model model) {
        List<CartItem> cartItems = defaultCartService.getAllProducts();
        BigDecimal totalPriceOfCart = defaultCartService.getTotalPriceOfCart();
        model.addAttribute("price", totalPriceOfCart);
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }
}

