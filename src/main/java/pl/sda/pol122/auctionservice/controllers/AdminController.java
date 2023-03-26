package pl.sda.pol122.auctionservice.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.pol122.auctionservice.model.User;
import pl.sda.pol122.auctionservice.services.AdminService;
import pl.sda.pol122.auctionservice.services.UserService;
import pl.sda.pol122.auctionservice.utils.PopUpMessage;

import java.util.Collection;

@Controller
@RequestMapping(path = "/admin")
@AllArgsConstructor
public class AdminController {
    private final UserService userService;

    private final AdminService adminService;

    @GetMapping("/userList/delete/{userName}")
    public String deleteAccount(@PathVariable String userName){
        adminService.deleteAccount(userName);
        return "redirect:/admin/userList";
    }


    @GetMapping("/createNewAdmin")
    public String loadCreateAdminForm(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();

        if(authorities.contains(new SimpleGrantedAuthority("SUPER_ADMIN"))){
            model.addAttribute("user", new User());
            return "createNewAdminAccount";
        }
        PopUpMessage.createPopUpMessage("Only super admin can create new admin.");
        return "redirect:/admin/adminSettings";
    }

    @PostMapping("/createNewAdmin")
    public String createAdminAccount(@Valid @ModelAttribute User user, BindingResult result){
        if(result.hasErrors()){
          return   "createNewAdminAccount";
        }
        userService.createAdminAccount(user);
        PopUpMessage.createPopUpMessage("New admin created!");
        return "redirect:/admin/adminSettings";
    }

    @GetMapping("/adminSettings")
    public String loadAdminSettings(){
        return "adminSettings";
    }

    @GetMapping("/userList")
    public String loadUserListForAdmin(Model model){
        model.addAttribute("users" , adminService.listOfUsers());
        return "userListForAdmin";
    }

    @GetMapping("/userList/banOrUnban/{id}")
    public String banOrUnbanUser(@PathVariable String id){
        adminService.banOrUnbanUser(Integer.valueOf(id));
        return "redirect:/admin/userList";
    }




}
