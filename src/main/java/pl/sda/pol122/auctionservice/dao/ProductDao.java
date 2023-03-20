package pl.sda.pol122.auctionservice.dao;

import pl.sda.pol122.auctionservice.entities.CategoryEntity;
import pl.sda.pol122.auctionservice.entities.ProductEntity;

import java.util.List;

public interface ProductDao {

    ProductEntity findProduct(Integer productId);

    List<ProductEntity> findProductsByCategory(CategoryEntity category);

}
