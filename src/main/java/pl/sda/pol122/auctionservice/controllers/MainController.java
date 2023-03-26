package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.pol122.auctionservice.model.User;
import pl.sda.pol122.auctionservice.model.UserAddress;
import pl.sda.pol122.auctionservice.services.CategoriesService;
import pl.sda.pol122.auctionservice.services.UserService;
import pl.sda.pol122.auctionservice.utils.AuthenticatedUserProvider;

import java.util.Collection;

@AllArgsConstructor
@Controller
@RequestMapping
public class MainController {

   private final CategoriesService categoriesService;

   private final AuthenticatedUserProvider authenticatedUser;
   private final UserService userService;

    @GetMapping(path = "/shop")
    public String loadCategoriesSite(Model model) {
        model.addAttribute("categoriesId", categoriesService.getAllAvailableCategories());
        return "shop";
    }

    @GetMapping("/login")
    public String showLoginFormToShop() {
        return "accountLogin";
    }

    @GetMapping("/logout")
    public String logoutUser() {
        return "redirect:/login";
    }

    @GetMapping(path = "/user/account")
    public String loadUserProfile(Model model) {

        if (authenticatedUser.checkIfLoggedUserIsAdmin() || authenticatedUser.checkIfLoggedUserIsSuperAdmin()){
            User authenticatedAdmin = userService.getAuthenticatedUser();
            if(authenticatedAdmin.getUserAddress() == null){
                model.addAttribute("adminAddress", new UserAddress());
            } else {
                model.addAttribute("adminAddress", authenticatedAdmin.getUserAddress());
            }
            model.addAttribute("admin", authenticatedAdmin);
            return "adminProfile";
        }

        User authenticatedUser = userService.getAuthenticatedUser();
        model.addAttribute("user", authenticatedUser);
        if(authenticatedUser.getUserAddress() == null){
            model.addAttribute("userAddress", new UserAddress());
        } else {
            model.addAttribute("userAddress", authenticatedUser.getUserAddress());
        }
        return "userProfile";
    }
}
