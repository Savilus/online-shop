package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.pol122.auctionservice.entities.ProductEntity;
import pl.sda.pol122.auctionservice.entities.UserEntity;
import pl.sda.pol122.auctionservice.services.DefaultProductService;
import pl.sda.pol122.auctionservice.services.DefaultUserService;

@Controller
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {

    private final DefaultUserService defaultUserService;
    private final DefaultProductService defaultProductService;

    @PostMapping
    public String addNewProductToSell(ProductEntity productEntity) {
        defaultProductService.addNewProduct(productEntity);
        return "redirect:/my-products";
    }

    @DeleteMapping
    public String deleteProduct(@PathVariable String id) {
        defaultProductService.deleteProductById(Integer.valueOf(id));
        return "redirect:/my-products";
    }

    @PatchMapping
    public String updateProductChanges(ProductEntity productEntity) {
        defaultProductService.updateProductChanges(productEntity);
        return "redirect:/my-products";
    }

    @PatchMapping
    public String updateAccountChanges(UserEntity userEntity) {
        defaultUserService.saveAccountChangesByUser(userEntity);
        return "redirect:/my-account";
    }


}
