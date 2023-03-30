package pl.sda.pol122.auctionservice.dao;

import org.springframework.stereotype.Repository;
import pl.sda.pol122.auctionservice.entities.CategoryEntity;
import pl.sda.pol122.auctionservice.entities.ProductEntity;

import java.util.List;

@Repository
public class ProductDaoDB implements ProductDao {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDaoDB(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public ProductEntity findProduct(Integer productId) {
        return productRepository.findProductEntityById(productId);
    }


    public List<ProductEntity> findProductsByCategory(CategoryEntity category) {

        CategoryEntity categoryEntity = categoryRepository.findById(category.getId()).orElseThrow();
        return productRepository.findAll()
                .stream()
                .filter(x -> x.getCategoryEntity() == categoryEntity)
                .toList();



    }

}
