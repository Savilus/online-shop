package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.pol122.auctionservice.entities.CategoryEntity;
import pl.sda.pol122.auctionservice.entities.ProductEntity;
import pl.sda.pol122.auctionservice.entities.UserEntity;
import pl.sda.pol122.auctionservice.model.Category;
import pl.sda.pol122.auctionservice.services.DefaultCategoriesService;
import pl.sda.pol122.auctionservice.services.DefaultProductService;
import pl.sda.pol122.auctionservice.services.DefaultUserService;

@Controller
@RequestMapping(path = "/admin")
@AllArgsConstructor
public class AdminController {

    private final DefaultCategoriesService defaultCategoriesService;
    private final DefaultUserService defaultUserService;
    private final DefaultProductService defaultProductService;

    @GetMapping("/add-category")
    public String addNewCategoryByAdmin(Category category) {
        defaultCategoriesService.addNewCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/users/{id}")
    public String deleteUserByAdmin(@PathVariable String id) {
        defaultUserService.deleteById(Integer.valueOf(id));
        return "redirect:/users";
    }

    @PatchMapping("/users/{id}")
    public String updateAccountStatusByAdmin(@PathVariable String id, boolean enabledFromInput) {
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

    @DeleteMapping
    public String deleteProductByAdmin(@PathVariable String productId) {
        defaultProductService.deleteProductById(Integer.valueOf(productId));
        return "redirect:/default";
    }


}
