package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.pol122.auctionservice.entities.UserEntity;
import pl.sda.pol122.auctionservice.model.Category;
import pl.sda.pol122.auctionservice.model.Product;
import pl.sda.pol122.auctionservice.services.CategoriesService;
import pl.sda.pol122.auctionservice.services.ProductService;
import pl.sda.pol122.auctionservice.services.UserService;

@Controller
@RequestMapping(path = "/admin")
@AllArgsConstructor
public class AdminController {

    private final CategoriesService categoriesService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/add-category")
    public String addNewCategoryByAdmin(Category category) {
        categoriesService.addNewCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/users/{id}")
    public String deleteUserByAdmin(@PathVariable String id) {
        userService.deleteById(Integer.valueOf(id));
        return "redirect:/users";
    }

    @GetMapping("/updateUser/{id}")
    public String updateAccountStatusByAdmin(@PathVariable String id, boolean enabledFromInput) {
        UserEntity userFromInput = userService.getUserById(Integer.valueOf(id));
        userFromInput.setEnabled(enabledFromInput);
        userService.saveAccountStatusByAdmin(userFromInput);
        return "redirect:/users";
    }

    @GetMapping("/updateProduct/{id}")
    public String updateProductChangesByAdmin(Product product, @PathVariable String id) {
        productService.updateProductChanges(product);
        return "redirect:/default";
    }

    @GetMapping("/product/{id}")
    public String deleteProductByAdmin(@PathVariable String id) {
        productService.deleteProductById(id);
        return "redirect:/default";
    }


}
