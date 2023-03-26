package pl.sda.pol122.auctionservice.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.pol122.auctionservice.model.User;
import pl.sda.pol122.auctionservice.model.UserAddress;
import pl.sda.pol122.auctionservice.services.UserService;
import pl.sda.pol122.auctionservice.utils.AuthenticatedUserProvider;
import pl.sda.pol122.auctionservice.utils.PopUpMessage;

@Controller
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/account/editAccount")
    public String editUserProfile(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("userAddress", new UserAddress());

        User authenticatedUser = userService.getAuthenticatedUser();
        model.addAttribute("loggedUser", authenticatedUser);
        return "editUserProfile";
    }

    @PostMapping("/account/editAccount")
    public String updateAccountChanges(@Valid @ModelAttribute User user, BindingResult result,
                                       @ModelAttribute UserAddress userAddress,
                                       Model model) {
        if(result.hasErrors()){
            return "editUserProfile";
        }
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
        if(AuthenticatedUserProvider.getLoggedUser() != null){
            PopUpMessage.createPopUpMessage("You can't create new account if you are logged.");
            return "redirect:/user/account";
        }
        model.addAttribute("user", new User());
        return "signUp";
    }

    @PostMapping(value = "/signUp")
    public String createNewUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "signUp";
        }
        model.addAttribute("user", user);
        userService.createUserAccount(user);
        return "redirect:/index";
    }
}
