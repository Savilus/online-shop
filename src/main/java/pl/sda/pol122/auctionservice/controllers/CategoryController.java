package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.pol122.auctionservice.model.Category;
import pl.sda.pol122.auctionservice.services.CategoriesService;
@Controller
@AllArgsConstructor
@RequestMapping(path = "/category")
public class CategoryController {

    private final CategoriesService categoriesService;



    @PostMapping("/createCategory")
    public String addNewCategory(@ModelAttribute Category category,
                                                Model model) {
        model.addAttribute("newCategory", category);
        categoriesService.addNewCategory(category);
        return "redirect:/category/createCategory";
    }

    @GetMapping("/createCategory")
    public String listOfCategories(Model model){
        model.addAttribute("categories",categoriesService.getAllCategories());
        model.addAttribute("newCategory", new Category());
        return "createNewCategory";
    }

    @GetMapping("/deleteCategory/{id}")
        public String deleteCategory(@PathVariable Integer id){
        categoriesService.setCategoryAvailability(id);
        return "redirect:/category/createCategory";
    }



}
