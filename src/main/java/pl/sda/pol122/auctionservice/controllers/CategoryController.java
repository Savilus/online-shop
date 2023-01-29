package pl.sda.pol122.auctionservice.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.pol122.auctionservice.model.Category;
import pl.sda.pol122.auctionservice.services.CategoriesService;

public class CategoryController {

    private final CategoriesService categoriesService;

    public CategoryController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @PostMapping("/add-category")
    public String addNewCategoryByAdmin(Category category) {
        categoriesService.addNewCategory(category);
        return "redirect:/categories";
    }
}
