package pl.sda.pol122.auctionservice.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.pol122.auctionservice.entities.UserEntity;
import pl.sda.pol122.auctionservice.model.User;
import pl.sda.pol122.auctionservice.services.AdminService;
import pl.sda.pol122.auctionservice.services.UserService;
import pl.sda.pol122.auctionservice.utils.AuthenticatedUserProvider;

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

    @PostMapping()
    public String createAdminAccount(@Valid @ModelAttribute User user){
        userService.createAdminAccount(user);
        return "redirect:/index";
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
