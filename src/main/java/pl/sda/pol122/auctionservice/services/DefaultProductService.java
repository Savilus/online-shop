package pl.sda.pol122.auctionservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.pol122.auctionservice.dao.CategoryDao;
import pl.sda.pol122.auctionservice.dao.ProductDao;
import pl.sda.pol122.auctionservice.dao.ProductRepository;
import pl.sda.pol122.auctionservice.entities.CategoryEntity;
import pl.sda.pol122.auctionservice.entities.ProductEntity;
import pl.sda.pol122.auctionservice.model.Category;
import pl.sda.pol122.auctionservice.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultProductService implements ProductService {

    private final ProductDao productDao;
    private final CategoryDao categoryDao;
    private final ProductRepository productRepository;
    private final CategoriesService categoriesService;

    @Override
    public List<Product> getListOfProducts(String categoryId) {
        CategoryEntity categoryById = categoryDao.getCategoryById(Integer.valueOf(categoryId));
        Category category = new Category(categoryById.getId(), categoryById.getCategoryName());

        List<ProductEntity> listOfProductsEntity = productDao.findProductsByCategory(categoryById);
        List<Product> productsByCategory = new ArrayList<>();

        for (int i = 0; i < listOfProductsEntity.size(); i++) {
            ProductEntity productEntity = listOfProductsEntity.get(i);
            Product product = new Product(productEntity.getId(),
                    productEntity.getName(),
                    productEntity.getPrice(),
                    category,
                    productEntity.getImage(),
                    productEntity.getAvailableAmount());


            productsByCategory.add(product);
        }

        return productsByCategory;
    }

    @Override
    public Product getProductById(String id) {
        ProductEntity productEntity = productDao.findProduct(Integer.valueOf(id));
        Category category =
                new Category(
                        productEntity.getCategoryEntity().getId(),
                        productEntity.getCategoryEntity().getCategoryName());

        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                category,
                productEntity.getImage(),
                productEntity.getAvailableAmount());
    }

    @Override
    public void deleteProductById(String id) {
        productRepository.deleteProductById(Integer.valueOf(id));

    }

    @Override
    public void addNewProduct(Product product) {
        ProductEntity productEntity =
                ProductEntity
                        .builder()
                        .name(product.getName())
                        .categoryEntity(categoriesService.getCategoryEntityById(product.getCategory().getId()))
                        .availableAmount(product.getAvailableAmount())
                        .price(product.getPrice())
                        .image(product.getImage())
                        .build();

        productRepository.save(productEntity);
    }

    @Override
    public void updateProductChanges(Product product) {
        ProductEntity productEntity =
                ProductEntity
                        .builder()
                        .name(product.getName())
                        .categoryEntity(categoriesService.getCategoryEntityById(product.getCategory().getId()))
                        .availableAmount(product.getAvailableAmount())
                        .price(product.getPrice())
                        .image(product.getImage())
                        .build();
        productRepository.save(productEntity);
    }


}
