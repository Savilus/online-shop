package pl.sda.pol122.auctionservice.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.pol122.auctionservice.enums.ERole;

import pl.sda.pol122.auctionservice.model.User;
import pl.sda.pol122.auctionservice.model.UserAddress;
import pl.sda.pol122.auctionservice.services.UserService;

@Controller
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/account/editAccount")
    public String editUserProfile(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("userAddress", new UserAddress());
        return "editUserProfile";
    }

    @PostMapping("/account/editAccount")
    public String updateAccountChanges(@ModelAttribute User user,
                                       @ModelAttribute UserAddress userAddress,
                                       Model model
                                       ) {



        model.addAttribute("user", user);
        model.addAttribute("userAddress", userAddress);

        userService.saveAccountChanges(user, userAddress);
        return "redirect:/user/account";
    }

    @GetMapping(path = "/account/orderHistory")
    public String loadUserOrderHistory(Model model) {
        model.addAttribute("orderHistory", userService.getUserOrderHistory());
        return "orderHistory";
    }

    @GetMapping("/signUp")
    public String showFormToSignUp(Model model) {
        model.addAttribute("user", new User());
        return "signUp";
    }

    @PostMapping(value = "/signUp")
    public String createNewUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if(result.hasErrors()){
            return "signUp";
        }
        model.addAttribute("user", user);
        userService.createUserAccount(user);
        return "redirect:/index";
    }
}
