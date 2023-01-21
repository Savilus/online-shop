package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.pol122.auctionservice.model.Product;
import pl.sda.pol122.auctionservice.services.CartService;
import pl.sda.pol122.auctionservice.services.ProductService;

@AllArgsConstructor
@Controller
@RequestMapping()
public class CartController {

    private final CartService cartService;

    private final ProductService productService;


    @PutMapping("/add/{productId}")
    public String addToCart (@PathVariable String productId){
        Product productById = productService.getProductById(productId);
        cartService.addProductToCart(productById);
        return "null";
    }


    @GetMapping(path = "/cart/checkout")
    public String loadCartCheckout(){
        return "checkout";
    }

    // load user cart - przyjmuje model i przerzuca dane z modelu do widoku cart
    @GetMapping(path = "/cart")
    public String loadUserCart(){
        return "cart";
    }
}
