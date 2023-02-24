package pl.sda.pol122.auctionservice.controllers;

import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.pol122.auctionservice.model.User;
import pl.sda.pol122.auctionservice.services.UserService;
import pl.sda.pol122.auctionservice.utils.AuthenticatedUserProvider;

@Controller
@RequestMapping(path = "/admin")
@AllArgsConstructor
public class AdminController {
    private final UserService userService;

    @DeleteMapping("/users/{id}")
    public String deleteAccountBySuperAdmin(@PathVariable String id) {
        if (AuthenticatedUserProvider.checkIfLoggedUserIsSuperAdmin()) {
            userService.deleteById(Integer.valueOf(id));
        } else {
            return "Your permissions do not allow you to delete account. " +
                    "If you want to delete your account please contact the site administrator.";
        }
        return "redirect:/users";
    }

    @PostMapping()
    public String createAdminAccount(@Valid @ModelAttribute User user, Model model){
        userService.createAdminAccount(user);

        return "redirect:/index";
    }

}
