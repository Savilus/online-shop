package pl.sda.pol122.auctionservice.services;

import pl.sda.pol122.auctionservice.entities.CategoryEntity;
import pl.sda.pol122.auctionservice.model.Category;

import java.util.List;

public interface CategoriesService {

    Category getCategoryById(Integer categoryId);

    List<Category> getAllAvailableCategories();

    List<Category> getAllCategories();

    void addNewCategory(Category category);
    CategoryEntity getCategoryEntityById(Integer id);

    void setCategoryAvailability(Integer id);

}
