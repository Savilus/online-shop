package pl.sda.pol122.auctionservice.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.sda.pol122.auctionservice.entities.ProductEntity;

import java.util.List;

public interface ProductDao {

    void saveProduct(ProductEntity product);

    ProductEntity findProduct(String productId);

    void deleteProduct(String productId);

    Page<ProductEntity> findProducts(Pageable pageable);
}
