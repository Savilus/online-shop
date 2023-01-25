package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.pol122.auctionservice.model.Category;

@AllArgsConstructor
@Controller
@RequestMapping
public class MainController {

    @GetMapping(path = "/shop")
    public String loadServiceSite(){
        return "shop";
    }

    @GetMapping(path = "/shop/product-list")
    public String loadProductList(){
        return "products";
    }

    @GetMapping(path = "/account")
    public String loadUserProfile(){
        return "userProfile";
    }

    @GetMapping(path = "/account/editAccount")
    public String editUserProfile(){
        return "editUserProfile";
    }





}
