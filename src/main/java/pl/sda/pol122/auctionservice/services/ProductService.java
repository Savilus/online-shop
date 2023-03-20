package pl.sda.pol122.auctionservice.services;

import pl.sda.pol122.auctionservice.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAvailableListOfProductsByCategoryId(Integer categoryId);

    List<Product> getAllProductsByCategoryId(Integer categoryId);

    Product getProductById(Integer productId);

    void setProductAvailability(Integer id);

    void addNewProduct(Product product);

    void updateProductChanges(Product product);

    List<Product> getRandomProducts();


}
