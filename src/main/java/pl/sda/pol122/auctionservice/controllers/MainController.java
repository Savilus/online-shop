package pl.sda.pol122.auctionservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping
public class MainController {


    @GetMapping(path = "/shop")
    public String loadServiceSite(){
        return "shop";
    }

    @GetMapping(path = "/shop/product-list")
    public String loadProductList(){
        return "products";
    }

    @GetMapping(path = "/shop/sportProducts")
    public String loadsportProductList(){
        return "sportProducts";
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
