package pl.sda.pol122.auctionservice.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.sda.pol122.auctionservice.dao.CategoryRepository;
import pl.sda.pol122.auctionservice.dao.ProductRepository;
import pl.sda.pol122.auctionservice.entities.CategoryEntity;
import pl.sda.pol122.auctionservice.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultCategoriesService implements CategoriesService {

    private final CategoryRepository categoryRepository;

    private final JdbcTemplate jdbcTemplate;


    @Override
    public Category getCategoryById(Integer categoryId) {
        Optional<CategoryEntity> byId = categoryRepository.findById(categoryId);
        return Category.builder().id(byId.get().getId())
                .categoryName(byId.get().getCategoryName())
                .image(byId.get().getImage())
                .build();
    }

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> allCategoriesDAO = categoryRepository.findAll();
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < allCategoriesDAO.size(); i++) {
            CategoryEntity categoryEntity = allCategoriesDAO.get(i);
            Category category = Category.builder()
                    .id(categoryEntity.getId())
                    .categoryName(categoryEntity.getCategoryName())
                    .image(categoryEntity.getImage())
                    .build();
            categories.add(category);
        }
        return categories;
    }

    @Override
    public void addNewCategory(Category category) {
        CategoryEntity categoryEntity = CategoryEntity
                .builder()
                .categoryName(category.getCategoryName())
                .listOfProducts(new ArrayList<>())
                .image(category.getImage())
                .build();
        categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity getCategoryEntityById(Integer entityId) {
        return categoryRepository.findCategoryEntityById(entityId);
    }

    @Override
    @Transactional
    public void deleteCategoryById(Integer id) {
        switchOffForeignKeyCheck();
        categoryRepository.deleteCategoryEntitiesById(id);
        switchOnForeignKeyCheck();
    }


    private void switchOffForeignKeyCheck() {
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=0");
    }

    private void switchOnForeignKeyCheck() {
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=1;");
    }
}
