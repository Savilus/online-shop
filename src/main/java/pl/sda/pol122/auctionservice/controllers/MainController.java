package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.pol122.auctionservice.model.Category;

@AllArgsConstructor
@Controller
@RequestMapping
public class MainController {

    @GetMapping(path = "/shop")
    public String loadServiceSite(Model model){
        Category house = new Category(1, "house");
        Category sport = new Category(2, "sportProducts");
        Category electronic = new Category(3, "electronic");
        model.addAttribute("sport", sport);
        model.addAttribute("house", house);
        model.addAttribute("electronic", electronic);
        return "shop";
    }

    @GetMapping(path = "/shop/product-list")
    public String loadProductList(){
        return "products";
    }


    @GetMapping(path = "/shop/houseProducts")
    public String loadHouseProductsList(){
        return "houseProducts";
    }
    @GetMapping(path = "/shop/electronicProducts")
    public String loadElectronicProducts(){
        return "electronicProducts";
    }



}
