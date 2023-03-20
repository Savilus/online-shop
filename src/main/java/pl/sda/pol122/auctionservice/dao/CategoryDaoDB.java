package pl.sda.pol122.auctionservice.dao;

import org.springframework.stereotype.Repository;
import pl.sda.pol122.auctionservice.entities.CategoryEntity;

@Repository
public class CategoryDaoDB implements CategoryDao {

    private final CategoryRepository categoryRepository;

    public CategoryDaoDB(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryEntity getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId).get();

    }
}
