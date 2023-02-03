package pl.sda.pol122.auctionservice.controllers;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.sda.pol122.auctionservice.model.User;
import pl.sda.pol122.auctionservice.services.UserService;

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
        userService.deleteById(Integer.valueOf(id));
        return "redirect:/users";
    }


    @PatchMapping("/updateUser/{userId}")
    public String updateAccountStatus(@PathVariable String userId, boolean enabledFromInput) {
        userService.saveAccountStatus(Integer.valueOf(userId), enabledFromInput);
        return "redirect:/users";
    }

    @GetMapping(path = "/account")
    public String loadUserProfile(Model model) {
        User authenticatedUser = userService.getAuthenticatedUser();
        model.addAttribute("user", authenticatedUser);
        return "userProfile";
    }

    @GetMapping(path = "/account/orderHistory")
    public String loadUserOrderHistory() {
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
