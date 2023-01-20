package pl.sda.pol122.auctionservice.services;

import pl.sda.pol122.auctionservice.model.ProductDto;

import java.util.List;

public interface CartService {


    boolean deleteProduct(String id);

    ProductDto addProductToCart(String id);

    List<ProductDto> getAllProducts();
}
