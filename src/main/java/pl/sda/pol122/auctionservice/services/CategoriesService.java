package pl.sda.pol122.auctionservice.services;

import pl.sda.pol122.auctionservice.model.Category;

import java.util.List;

public interface CategoriesService {

    Category getCategoryById(String id);

    List<Category> getAllCategories();
}
