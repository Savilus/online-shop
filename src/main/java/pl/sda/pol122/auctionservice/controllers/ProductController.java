package pl.sda.pol122.auctionservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.pol122.auctionservice.dao.ProductRepository;
import pl.sda.pol122.auctionservice.entities.ProductEntity;
import pl.sda.pol122.auctionservice.model.Product;
import pl.sda.pol122.auctionservice.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final ProductRepository productRepository;

    public ProductController(ProductService productService,
                             ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("/details/{productId}")
    public String getProductsDetails(Model model,@PathVariable String productId){
        model.addAttribute("product", productService.getProductById(productId));
        return "products_details";
    }
    @GetMapping("/{categoryId}")
    public String getAllProducts(Model model,@PathVariable String categoryId){
        List<Product> allProductsOfCategory = productService.getListOfProducts(categoryId);
        model.addAttribute("allProducts", allProductsOfCategory);
        return "product_list";
    }

    @PostMapping()
    public String addProduct(ProductEntity productEntity){
        productRepository.save(productEntity);
        return "redirect:/products";
    }





}
