package pl.sda.pol122.auctionservice.services;

import jakarta.transaction.Transactional;
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
    public List<Product> getAvailableListOfProductsByCategoryId(Integer categoryId) {
        CategoryEntity categoryById = categoryDao.getCategoryById(categoryId);
        Category category = Category.builder()
                .id(categoryById.getId())
                .categoryName(categoryById.getCategoryName())
                .build();

        List<ProductEntity> listOfProductsEntity = productDao.findProductsByCategory(categoryById);
        List<Product> productsByCategory = new ArrayList<>();

        for (int i = 0; i < listOfProductsEntity.size(); i++) {
            ProductEntity productEntity = listOfProductsEntity.get(i);
            if(Boolean.TRUE.equals(productEntity.getEnabled())){
                Product product = new Product(productEntity.getId(),
                        productEntity.getName(),
                        productEntity.getPrice(),
                        category,
                        productEntity.getImage(),
                        productEntity.getAvailableAmount(),
                        productEntity.getEnabled());


                productsByCategory.add(product);
            }

        }
        return productsByCategory;
    }

    @Override
    public List<Product> getAllProductsByCategoryId(Integer categoryId) {
        CategoryEntity categoryById = categoryDao.getCategoryById(categoryId);
        Category category = Category.builder()
                .id(categoryById.getId())
                .categoryName(categoryById.getCategoryName())
                .build();

        List<ProductEntity> listOfProductsEntity = productDao.findProductsByCategory(categoryById);
        List<Product> productsByCategory = new ArrayList<>();

        for (int i = 0; i < listOfProductsEntity.size(); i++) {
            ProductEntity productEntity = listOfProductsEntity.get(i);
                Product product = new Product(productEntity.getId(),
                        productEntity.getName(),
                        productEntity.getPrice(),
                        category,
                        productEntity.getImage(),
                        productEntity.getAvailableAmount(),
                        productEntity.getEnabled());


                productsByCategory.add(product);


        }
        return productsByCategory;
    }

    @Override
    public Product getProductById(Integer productId) {
        ProductEntity productEntity = productDao.findProduct(productId);
        Category category = Category.builder()
                .id(productEntity.getCategoryEntity().getId())
                .categoryName(productEntity.getCategoryEntity().getCategoryName())
                .build();


        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                category,
                productEntity.getImage(),
                productEntity.getAvailableAmount(),
                productEntity.getEnabled());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void setProductAvailability(Integer id) {
        ProductEntity productEntityById = productRepository.findProductEntityById(id);
        productRepository.setProductAvailability(!productEntityById.getEnabled(), id);
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
                        .enabled(true)
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
                        .enabled(product.getEnabled())
                        .build();
        productRepository.save(productEntity);
    }

    @Override
    public List<Product> getRandomProducts() {
        List<ProductEntity> randomProductsById = productRepository.findRandomProductsById();
        List<Product> randomProducts = new ArrayList<>();
        randomProductsById
                .forEach(productEntity -> randomProducts.add(Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .image(productEntity.getImage())
                .category(Category.builder()
                        .id(productEntity.getCategoryEntity().getId())
                        .categoryName(productEntity.getCategoryEntity().getCategoryName())
                        .image(productEntity.getCategoryEntity().getImage())
                        .build())
                .availableAmount(productEntity.getAvailableAmount())
                .build()));
        return randomProducts;
    }


}
