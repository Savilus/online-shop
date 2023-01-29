package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.pol122.auctionservice.model.Category;
import pl.sda.pol122.auctionservice.model.Product;
import pl.sda.pol122.auctionservice.services.CategoriesService;
import pl.sda.pol122.auctionservice.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
    public String loadAllProducts(Model model,@PathVariable String categoryId) {
        List<Product> allProducts = productService.getListOfProductsByCategoryId(Integer.valueOf(categoryId));
        Category categoryById = categoriesService.getCategoryById(Integer.valueOf(categoryId));
        model.addAttribute("categoryName", categoryById);
        model.addAttribute("allProducts", allProducts);
        return "allProductsList";
    }

    @GetMapping()
    public String addProduct(Product product) {
        productService.addNewProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/index")
    public String showRandomProducts(Model model){

        List<Product> randomProducts = productService.getRandomProducts();
        model.addAttribute("randomProducts" , randomProducts);
        return "/index";
    }

    @DeleteMapping("/product/{id}")
    public String deleteProductByAdmin(@PathVariable String id) {
        productService.deleteProductById(Integer.valueOf(id));
        return "redirect:/default";
    }


    @PatchMapping("/updateProduct")
    public String updateProductChanges(Product product) {
        productService.updateProductChanges(product);
        return "redirect:/my-products";
    }

    @DeleteMapping("/deleteProduct")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProductById(Integer.valueOf(id));
        return "redirect:/my-products";
    }

    @PostMapping("/addNewProduct")
    public String addNewProductToSell(Product product) {
        productService.addNewProduct(product);
        return "redirect:/my-products";
    }


}
