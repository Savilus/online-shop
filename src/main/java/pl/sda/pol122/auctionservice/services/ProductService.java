package pl.sda.pol122.auctionservice.services;

import pl.sda.pol122.auctionservice.entities.ProductEntity;
import pl.sda.pol122.auctionservice.model.Product;

import java.util.List;

public interface ProductService{

    List<Product> getListOfProducts(Integer categoryId);

    Product getProductById(Integer productId);
    void deleteProductById(Integer productId);

    void addNewProduct(Product product);

    void updateProductChanges(Product product);





}
