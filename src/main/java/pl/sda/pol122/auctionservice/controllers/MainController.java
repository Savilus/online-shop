package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.pol122.auctionservice.services.CategoriesService;
import pl.sda.pol122.auctionservice.services.UserService;

@AllArgsConstructor
@Controller
@RequestMapping
public class MainController {

    CategoriesService categoriesService;
    UserService userService;

    @GetMapping(path = "/shop")
    public String loadCategoriesSite(Model model) {
        model.addAttribute("categoriesId", categoriesService.getAllCategories());
        return "shop";
    }

    @GetMapping(path = "/account/editAccount")
    public String editUserProfile() {
        return "editUserProfile";
    }

    @GetMapping(path = "/login")
    public String logInOrSignUpAccount(){

        return "accountLogin";
    }


}
