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
import pl.sda.pol122.auctionservice.services.CategoriesService;
import pl.sda.pol122.auctionservice.services.UserService;

import java.util.Collection;

@AllArgsConstructor
@Controller
@RequestMapping
public class MainController {

   private final CategoriesService categoriesService;
   private final UserService userService;

    @GetMapping(path = "/shop")
    public String loadCategoriesSite(Model model) {
        model.addAttribute("categoriesId", categoriesService.getAllCategories());
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();

        if (authorities.contains(new SimpleGrantedAuthority("ADMIN")) ||
                authorities.contains(new SimpleGrantedAuthority("SUPER_ADMIN"))){
            User authenticatedAdmin = userService.getAuthenticatedUser();
            model.addAttribute("user", authenticatedAdmin);
            return "adminProfile";
        }

        User authenticatedUser = userService.getAuthenticatedUser();
        model.addAttribute("user", authenticatedUser);
        return "userProfile";
    }
}
