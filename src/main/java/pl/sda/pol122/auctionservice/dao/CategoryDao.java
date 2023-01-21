package pl.sda.pol122.auctionservice.dao;

import pl.sda.pol122.auctionservice.entities.CategoryEntity;
import pl.sda.pol122.auctionservice.entities.ProductEntity;

import java.util.List;

public interface CategoryDao {

   void saveCategory(CategoryEntity category);

   List<ProductEntity> findProductsOfCategory(Integer categoryId);

   void deleteCategory(Integer categoryId);

}
