package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.pol122.auctionservice.model.Product;
import pl.sda.pol122.auctionservice.services.ProductService;

import java.util.List;

@Controller
@RequestMapping()
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("product/details/{productId}")
    public String getProductsDetails(Model model, @PathVariable String productId) {
        model.addAttribute("product", productService.getProductById(Integer.valueOf(productId)));
        return "productDetails";
    }

    @GetMapping(path = "/shop/allProducts/{categoryId}")
    public String loadAllProducts(Model model,@PathVariable String categoryId) {
        List<Product> allProducts = productService.getListOfProducts(categoryId);
        model.addAttribute("allProducts", allProducts);
        return "allProductsList";
    }

    @GetMapping()
    public String addProduct(Product product) {
        productService.addNewProduct(product);
        return "redirect:/products";
    }


}
