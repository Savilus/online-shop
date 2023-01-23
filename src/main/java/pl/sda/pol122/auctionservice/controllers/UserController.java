package pl.sda.pol122.auctionservice.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.pol122.auctionservice.entities.ProductEntity;
import pl.sda.pol122.auctionservice.entities.UserEntity;
import pl.sda.pol122.auctionservice.services.DefaultProductService;
import pl.sda.pol122.auctionservice.services.DefaultUserService;

public class UserController {

    private final DefaultUserService defaultUserService;
    private final DefaultProductService defaultProductService;

    public UserController(DefaultUserService defaultUserService, DefaultProductService defaultProductService) {
        this.defaultUserService = defaultUserService;
        this.defaultProductService = defaultProductService;
    }

    @PostMapping
    public String addNewProductToSell(ProductEntity productEntity){
        defaultProductService.addNewProduct(productEntity);
        return "redirect:/my-products";
    }
    @DeleteMapping
    public String deleteProduct(@PathVariable String id){
        defaultProductService.deleteProductById(Integer.valueOf(id));
        return "redirect:/my-products";
    }

    @PatchMapping
    public String updateProductChanges(ProductEntity productEntity){
        defaultProductService.updateProductChanges(productEntity);
        return "redirect:/my-products";
    }

    @PatchMapping
    public String updateAccountChanges(UserEntity userEntity){
        defaultUserService.saveAccountChangesByUser(userEntity);
        return "redirect:/my-account";
    }


}
