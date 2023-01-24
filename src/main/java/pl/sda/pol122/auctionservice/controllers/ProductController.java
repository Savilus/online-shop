package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.pol122.auctionservice.entities.ProductEntity;
import pl.sda.pol122.auctionservice.model.Product;
import pl.sda.pol122.auctionservice.services.DefaultProductService;

import java.util.List;

@Controller
@RequestMapping()
@AllArgsConstructor
public class ProductController {

    private final DefaultProductService defaultProductService;


    @GetMapping("product/details/{productId}")
    public String getProductsDetails(Model model, @PathVariable Integer productId) {
        model.addAttribute("product", defaultProductService.getProductById(productId));
        return "productDetails";
    }


    @GetMapping(path = "/shop/houseProducts")
    public String loadHouseProductsList(Model model) {
        List<Product> allHouseProducts = defaultProductService.getListOfProducts(1);
        model.addAttribute("houseProducts", allHouseProducts);
        return "houseProducts";
    }

    @GetMapping(path = "/shop/sportProducts")
    public String loadSportProductList(Model model) {
        List<Product> allSportProducts = defaultProductService.getListOfProducts(2);
        model.addAttribute("sportProducts", allSportProducts);
        return "sportProducts";
    }

    @GetMapping(path = "/shop/electronicProducts")
    public String loadElectronicProducts(Model model) {
        List<Product> allElectronicProducts = defaultProductService.getListOfProducts(3);
        model.addAttribute("electronicProducts", allElectronicProducts);
        return "electronicProducts";
    }

    // - dolozyc warstwe przez service
    @PostMapping()
    public String addProduct(ProductEntity productEntity) {
        defaultProductService.addNewProduct(productEntity);
        return "redirect:/products";
    }


}
