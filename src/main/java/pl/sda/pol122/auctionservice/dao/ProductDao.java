package pl.sda.pol122.auctionservice.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.sda.pol122.auctionservice.entities.CategoryEntity;
import pl.sda.pol122.auctionservice.entities.ProductEntity;
import pl.sda.pol122.auctionservice.model.Category;

import java.util.List;

public interface ProductDao {

    void saveProduct(ProductEntity product);

    ProductEntity findProduct(String productId);

    void deleteProduct(String productId);

    List<ProductEntity> findProducts(CategoryEntity category);
}
