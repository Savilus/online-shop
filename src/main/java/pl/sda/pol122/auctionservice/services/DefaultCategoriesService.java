package pl.sda.pol122.auctionservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.pol122.auctionservice.dao.CategoryRepository;
import pl.sda.pol122.auctionservice.entities.CategoryEntity;
import pl.sda.pol122.auctionservice.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultCategoriesService implements CategoriesService {

    private final CategoryRepository categoryRepository;


    @Override
    public Category getCategoryById(Integer categoryId) {
        Optional<CategoryEntity> byId = categoryRepository.findById(categoryId);
        return new Category(byId.get().getId(), byId.get().getCategoryName(), byId.get().getImage());
    }

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> allCategoriesDAO = categoryRepository.findAll();
        List<Category> categories = new ArrayList<>();
        for(int i = 0; i < allCategoriesDAO.size(); i++){
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
                .build();
        categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity getCategoryEntityById(Integer entityId) {
        return categoryRepository.findCategoryEntityById(entityId);
    }
}
