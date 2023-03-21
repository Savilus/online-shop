package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.pol122.auctionservice.model.Category;
import pl.sda.pol122.auctionservice.model.Product;
import pl.sda.pol122.auctionservice.services.CategoriesService;
import pl.sda.pol122.auctionservice.services.ProductService;

import java.util.List;

@Controller
@RequestMapping()
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final CategoriesService categoriesService;


    @GetMapping("product/details/{productId}")
    public String getProductsDetails(Model model, @PathVariable String productId) {
        model.addAttribute("product", productService.getProductById(Integer.valueOf(productId)));
        return "productDetails";
    }

    @GetMapping(path = "/shop/allProducts/{categoryId}")
    public String loadAllProducts(Model model, @PathVariable Integer categoryId) {
        List<Product> allProducts = productService.getAvailableListOfProductsByCategoryId(categoryId);
        Category categoryById = categoriesService.getCategoryById(categoryId);
        model.addAttribute("categoryName", categoryById);
        model.addAttribute("allProducts", allProducts);
        return "allProductsList";
    }

    @GetMapping("/productChanges/category/{id}")
    public String listOfProductsFromCategoryForAdmin(@PathVariable Integer id,
                                                     Model model) {
        List<Product> allProducts = productService.getAllProductsByCategoryId(id);
        Category categoryById = categoriesService.getCategoryById(id);
        model.addAttribute("categoryName", categoryById);
        model.addAttribute("allProducts", allProducts);
        model.addAttribute("product", new Product());
        return "editProducts";
    }

    @GetMapping("/productChanges")
    public String categoryListToAddProducts(Model model) {
        model.addAttribute("categories", categoriesService.getAllCategories());
        return "categoryListToAddProducts";
    }

    @GetMapping(path = {"/", "/index"})
    public String showRandomProducts(Model model) {
        List<Product> randomProducts = productService.getRandomProducts();
        List<Category> allCategories = categoriesService.getAllAvailableCategories();
        model.addAttribute("categories", allCategories);
        model.addAttribute("randomProducts", randomProducts);

        return "/index";
    }

    @GetMapping("/productChanges/availability/{id}")
    public String switchAvailabilityForProduct(@PathVariable Integer id) {
        Product productById = productService.getProductById(id);
        productService.setProductAvailability(id);
        return "redirect:/productChanges/category/" + productById.getCategory().getId();
    }


    @PostMapping("/productChanges/category/{id}")
    public String addOrUpdateProductByAdmin(@ModelAttribute Product product, Model model, @PathVariable Integer id) {
        model.addAttribute("product", product);
        Category categoryById = categoriesService.getCategoryById(id);
        product.setCategory(categoryById);
        productService.addNewProduct(product);
        return "redirect:/productChanges/category/" + id;
    }


}
