package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.pol122.auctionservice.entities.UserEntity;
import pl.sda.pol122.auctionservice.model.Product;
import pl.sda.pol122.auctionservice.services.ProductService;
import pl.sda.pol122.auctionservice.services.UserService;

@Controller
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProductService productService;

    @PostMapping("/addNewProduct")
    public String addNewProductToSell(Product product) {
        productService.addNewProduct(product);
        return "redirect:/my-products";
    }

    @DeleteMapping("/deleteProduct")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProductById(Integer.valueOf(id));
        return "redirect:/my-products";
    }

    @PatchMapping("/updateProduct")
    public String updateProductChanges(Product product) {
        productService.updateProductChanges(product);
        return "redirect:/my-products";
    }

    @PatchMapping("/")
    public String updateAccountChanges(UserEntity userEntity) {
        userService.saveAccountChangesByUser(userEntity);
        return "redirect:/my-account";
    }

    @PatchMapping(path = "/account/editAccount")
    public String editUserProfile() {
        return "editUserProfile";
    }


}
