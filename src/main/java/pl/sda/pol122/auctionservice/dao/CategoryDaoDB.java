package pl.sda.pol122.auctionservice.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Repository;
import pl.sda.pol122.auctionservice.entities.CategoryEntity;
import pl.sda.pol122.auctionservice.entities.ProductEntity;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryDaoDB implements CategoryDao {

    private final CategoryRepository categoryRepository;

    public CategoryDaoDB(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void saveCategory(CategoryEntity category) {
        categoryRepository.save(category);
    }

    @Override
    public List<ProductEntity> findProductsOfCategory(Integer categoryId) {
        CategoryEntity categoryEntityById = categoryRepository.findById(categoryId).get();

        return categoryEntityById.getListOfProducts();
    }


    @Override
    public void deleteCategory(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public CategoryEntity getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId).get();
    }
}
