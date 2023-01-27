package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping
    public String addNewProductToSell(Product product) {
        productService.addNewProduct(product);
        return "redirect:/my-products";
    }

    @GetMapping
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProductById(id);
        return "redirect:/my-products";
    }

    @GetMapping
    public String updateProductChanges(Product product) {
        productService.updateProductChanges(product);
        return "redirect:/my-products";
    }

    @GetMapping("/")
    public String updateAccountChanges(UserEntity userEntity) {
        userService.saveAccountChangesByUser(userEntity);
        return "redirect:/my-account";
    }

    @GetMapping(path = "/account/editAccount")
    public String editUserProfile() {
        return "editUserProfile";
    }


}
