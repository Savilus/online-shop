package pl.sda.pol122.auctionservice.services;

import pl.sda.pol122.auctionservice.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getListOfProducts(Integer categoryId);

    Product getProductById(String id);

}
