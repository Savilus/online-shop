package pl.sda.pol122.auctionservice.controllers;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.sda.pol122.auctionservice.enums.ERole;
import pl.sda.pol122.auctionservice.model.Order;
import pl.sda.pol122.auctionservice.model.Product;

import pl.sda.pol122.auctionservice.model.User;
import pl.sda.pol122.auctionservice.services.UserService;
import pl.sda.pol122.auctionservice.utils.AuthenticatedUserProvider;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PatchMapping("/")
    public String updateAccountChanges(User user) {
        List<ObjectError> allErrors = userService.validatePasswordAndLogin(user);
        if (!allErrors.isEmpty()) {
            return new Gson().toJson(allErrors);
        } else {
            userService.saveAccountChanges(user);
            return "redirect:/my-account";
        }
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
            return "Your permissions do not allow you to delete account or user doesn't exist. " +
                    "If you want to delete your account please contact the site administrator.";
        }
        return "redirect:/users";
    }


    @PatchMapping("/updateUser/{userId}")
    public String updateAccountStatus(@PathVariable String userId, boolean enabledFromInput) {
        if (AuthenticatedUserProvider.checkIfLoggedUserIsAdmin() ||
                AuthenticatedUserProvider.checkIfLoggedUserIsSuperAdmin()) {
            userService.saveAccountStatus(Integer.valueOf(userId), enabledFromInput);
        } else {
            return "Unauthorized! Only the admin can change account status. Please contact with the site administrator.";
        }
        return "redirect:/users";
    }

    @GetMapping(path = "/account")
    public String loadUserProfile(Model model) {
        User authenticatedUser = userService.getAuthenticatedUser();
        model.addAttribute("user", authenticatedUser);
        return "userProfile";
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
    public String createNewUser(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        // not working yet
//        List<ObjectError> allErrors = userService.validatePasswordAndLogin(user);
//        if (!allErrors.isEmpty()) {
//            return new Gson().toJson(allErrors);
//        } else {
//        }
        userService.createUserAccount(user);
        return "redirect:/index";
    }
}
