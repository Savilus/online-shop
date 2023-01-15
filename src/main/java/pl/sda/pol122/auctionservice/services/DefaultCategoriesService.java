package pl.sda.pol122.auctionservice.services;

import org.springframework.stereotype.Service;
import pl.sda.pol122.auctionservice.dao.CategoryRepository;
import pl.sda.pol122.auctionservice.model.Category;

import java.util.List;

@Service
public class DefaultCategoriesService implements CategoriesService {

    private final CategoryRepository categoryRepository;

    public DefaultCategoriesService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryById(String id) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }
}
