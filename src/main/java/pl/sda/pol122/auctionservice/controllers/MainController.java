package pl.sda.pol122.auctionservice.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.pol122.auctionservice.model.User;
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

    @GetMapping("/login")
    public String showCreateUserFormOrLoginToShop(Model model){
        model.addAttribute("user", new User());
        return "accountLogin";
    }

    @GetMapping("/signUp")
    public String showFormToSignUp(Model model){
        model.addAttribute("user", new User());
        return "signUp";
    }

    @PostMapping(value = "/signUp")
    public String createNewUser(User user, Model model,
                                BindingResult bindingResult){
        model.addAttribute("user", user);
        if(bindingResult.hasErrors()){
            return "redirect:/login";
        }

        userService.createUserAccount(user);
        return "redirect:/index";
    }

}
