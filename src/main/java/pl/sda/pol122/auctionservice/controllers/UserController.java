package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.pol122.auctionservice.model.Category;
import pl.sda.pol122.auctionservice.model.Product;
import pl.sda.pol122.auctionservice.model.User;
import pl.sda.pol122.auctionservice.services.CategoriesService;
import pl.sda.pol122.auctionservice.services.ProductService;
import pl.sda.pol122.auctionservice.services.UserService;

@Controller
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PatchMapping("/")
    public String updateAccountChanges(User user) {
        userService.saveAccountChangesByUser(user);
        return "redirect:/my-account";
    }

    @PatchMapping(path = "/account/editAccount")
    public String editUserProfile() {
        return "editUserProfile";
    }


    @DeleteMapping("/users/{id}")
    public String deleteUserByAdmin(@PathVariable String id) {
        userService.deleteById(Integer.valueOf(id));
        return "redirect:/users";
    }


    @PatchMapping("/updateUser/{userId}")
    public String updateAccountStatusByAdmin(@PathVariable String userId, boolean enabledFromInput) {
        userService.saveAccountStatusByAdmin(Integer.valueOf(userId), enabledFromInput);
        return "redirect:/users";
    }






}
