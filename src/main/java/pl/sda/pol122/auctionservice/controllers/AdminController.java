package pl.sda.pol122.auctionservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.pol122.auctionservice.entities.CategoryEntity;
import pl.sda.pol122.auctionservice.entities.ProductEntity;
import pl.sda.pol122.auctionservice.entities.UserEntity;
import pl.sda.pol122.auctionservice.services.DefaultCategoriesService;
import pl.sda.pol122.auctionservice.services.DefaultProductService;
import pl.sda.pol122.auctionservice.services.DefaultUserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final DefaultCategoriesService categoriesService;
    private final DefaultUserService defaultUserService;
    private final DefaultProductService defaultProductService;

    public AdminController(DefaultCategoriesService categoriesService,
                           DefaultUserService defaultUserService, DefaultProductService defaultProductService) {
        this.categoriesService = categoriesService;
        this.defaultUserService = defaultUserService;

        this.defaultProductService = defaultProductService;
    }

    @PostMapping("/add-category")
    public String addNewCategory(CategoryEntity categoryEntity) {
        categoriesService.addNewCategory(categoryEntity);
        return "redirect:/categories";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable String id) {
        defaultUserService.deleteById(Integer.valueOf(id));
        return "redirect:/users";
    }

    @PatchMapping("/users/{id}")
    public String updateAccountStatus(@PathVariable String id, boolean enabledFromInput) {
        UserEntity userFromInput = defaultUserService.getUserById(Integer.valueOf(id));
        userFromInput.setEnabled(enabledFromInput);
        defaultUserService.saveAccountStatusByAdmin(userFromInput);
        return "redirect:/users";
    }

    @PatchMapping
    public String updateProductChangesByAdmin(ProductEntity productEntity) {
        defaultProductService.updateProductChanges(productEntity);
        return "redirect:/default";
    }


}
