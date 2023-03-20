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
    public String loadAllProducts(Model model,@PathVariable Integer categoryId) {
        List<Product> allProducts = productService.getAvailableListOfProductsByCategoryId(categoryId);
        Category categoryById = categoriesService.getCategoryById(categoryId);
        model.addAttribute("categoryName", categoryById);
        model.addAttribute("allProducts", allProducts);
        return "allProductsList";
    }

    @GetMapping("/productChanges/category/{categoryId}")
    public String listOfProductsCategoryForAdmin(@PathVariable Integer categoryId,
                                                 Model model) {
        List<Product> allProducts = productService.getAllProductsByCategoryId(categoryId);
        Category categoryById = categoriesService.getCategoryById(categoryId);
        model.addAttribute("categoryName", categoryById);
        model.addAttribute("allProducts", allProducts);
        return "editProducts";
    }

    @GetMapping("/productChanges")
    public String categoryListToAddProducts(Model model){
        model.addAttribute("categories", categoriesService.getAllCategories());
        return "categoryListToAddProducts";
    }

    @GetMapping(path = {"/" , "/index"})
    public String showRandomProducts(Model model){
        List<Product> randomProducts = productService.getRandomProducts();
        List<Category> allCategories = categoriesService.getAllAvailableCategories();
        model.addAttribute("categories", allCategories);
        model.addAttribute("randomProducts" , randomProducts);

        return "/index";
    }

    @DeleteMapping("/product/{id}")
    public String deleteProductByAdmin(@PathVariable String id) {
        return "redirect:/default";
    }


    @GetMapping("/productChanges/availability/{id}")
    public String switchAvailabilityForProduct(@PathVariable Integer id) {
        Product productById = productService.getProductById(id);
        productService.setProductAvailability(id);
        return "redirect:/productChanges/category/" + productById.getCategory().getId();
    }


    @PostMapping("/addNewProduct")
    public String addNewProductToSell(Product product) {
        productService.addNewProduct(product);
        return "redirect:/my-products";
    }



}
