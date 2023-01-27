package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.pol122.auctionservice.services.CategoriesService;

@AllArgsConstructor
@Controller
@RequestMapping
public class MainController {

    CategoriesService categoriesService;

    @GetMapping(path = "/shop")
    public String loadCategoriesSite(Model model){
        model.addAttribute("categoriesId", categoriesService.getAllCategories());
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
