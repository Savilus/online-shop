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


    @GetMapping(path = "/shop/productsList/{categoryId}")
    public String loadHouseProductsList(Model model, @PathVariable String categoryId) {
        List<Product> allProducts = productService.getListOfProducts(Integer.valueOf(categoryId));
        model.addAttribute("allProducts", allProducts);
        return "houseProducts";
    }

    @GetMapping(path = "/shop/sportProducts")
    public String loadSportProductList(Model model) {
        List<Product> allSportProducts = productService.getListOfProducts(2);
        model.addAttribute("sportProducts", allSportProducts);
        return "sportProducts";
    }

    @GetMapping(path = "/shop/electronicProducts")
    public String loadElectronicProducts(Model model) {
        List<Product> allElectronicProducts = productService.getListOfProducts(3);
        model.addAttribute("electronicProducts", allElectronicProducts);
        return "electronicProducts";
    }

    @GetMapping()
    public String addProduct(Product product) {
        productService.addNewProduct(product);
        return "redirect:/products";
    }


}
