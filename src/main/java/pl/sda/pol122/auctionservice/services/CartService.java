package pl.sda.pol122.auctionservice.services;

import org.springframework.stereotype.Service;
import pl.sda.pol122.auctionservice.model.ProductDto;

import java.util.List;

public interface CartService {


    boolean deleteProduct(String id);

    ProductDto addMoreProductsToCart(String id);

    List<ProductDto> getAllProducts();
}
