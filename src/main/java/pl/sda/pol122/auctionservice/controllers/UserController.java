package pl.sda.pol122.auctionservice.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.pol122.auctionservice.enums.ERole;
import pl.sda.pol122.auctionservice.model.User;
import pl.sda.pol122.auctionservice.services.UserService;
import pl.sda.pol122.auctionservice.utils.AuthenticatedUserProvider;

@Controller
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PatchMapping("/")
    public String updateAccountChanges(@Valid User user) {
        userService.saveAccountChanges(user);
        return "redirect:/my-account";
    }

    @GetMapping(path = "/account/editAccount")
    public String editUserProfile() {
        return "editUserProfile";
    }


    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable String id) {
        if ((AuthenticatedUserProvider.checkIfLoggedUserIsAdmin() ||
                AuthenticatedUserProvider.checkIfLoggedUserIsSuperAdmin()) &&
                !userService.getUserById(Integer.valueOf(id)).getRoles().contains(ERole.ADMIN) ||
                !userService.getUserById(Integer.valueOf(id)).getRoles().contains(ERole.SUPER_ADMIN)
        ) {
            userService.deleteById(Integer.valueOf(id));
        } else {
            return "redirect:/index";
        }
        return "redirect:/users";
    }


    @PatchMapping("/updateUser/{userId}")
    public String updateAccountStatus(@PathVariable String userId, boolean enabledFromInput) {
        if (AuthenticatedUserProvider.checkIfLoggedUserIsAdmin() ||
                AuthenticatedUserProvider.checkIfLoggedUserIsSuperAdmin()) {
            userService.saveAccountStatus(Integer.valueOf(userId), enabledFromInput);
        } else {
            return "redirect:/index";
        }
        return "redirect:/users";
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
