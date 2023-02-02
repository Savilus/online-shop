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

//    @GetMapping(path = "/login")
//    public String logInOrSignUpAccount(){
//
//        return "accountLogin";
//    }

    @GetMapping(value = "/login")
    public String showCreateUserFormOrLoginToShop(Model model){
        model.addAttribute("user", new User());
        return "accountLogin";
    }

    @PostMapping(value = "/login")
    public String createNewUser(@ModelAttribute("user") @Valid User user,
                                BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "redirect:/login";
        }

        userService.createUserAccount(user);
        return "redirect:/index";
    }

}
