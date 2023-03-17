package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.pol122.auctionservice.model.Category;
import pl.sda.pol122.auctionservice.services.CategoriesService;
@Controller
@AllArgsConstructor
@RequestMapping(path = "/category")
public class CategoryController {

    private final CategoriesService categoriesService;



    @PostMapping("/add-category")
    public String addNewCategory(Category category) {
        categoriesService.addNewCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/createCategory")
    public String listOfCategories(Model model){
        model.addAttribute("categories",categoriesService.getAllCategories());
        return "createNewCategory";
    }

    @PostMapping("/createCategory")
    public String createNewCategory(Category category){
        categoriesService.addNewCategory(category);
        return "redirect:/";
    }
}
