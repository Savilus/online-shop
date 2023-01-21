package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.pol122.auctionservice.services.CartService;

@AllArgsConstructor
@Controller
@Slf4j
@RequestMapping
public class UserController {

    private final CartService cartService;

    @GetMapping(path = "/cart")
    public String loadUserCart(){
        return "cart";
    }

    @GetMapping(path = "/index")
    public String loadHomeSite(){
        return "index";
    }

    @GetMapping(path = "/shop")
    public String loadServiceSite(){
        return "shop";
    }

    @GetMapping(path = "/about")
    public String loadAboutSite(){
        return "about";
    }

    @GetMapping(path = "/why")
    public String loadWhyUsSite(){
        return "why";
    }

    @GetMapping(path = "/shop/product-list")
    public String loadProductList(){
        return "products";
    }

    @GetMapping(path = "/cart/checkout")
    public String loadCartCheckout(){
        return "checkout";
    }

}
