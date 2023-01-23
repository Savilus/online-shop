package pl.sda.pol122.auctionservice.services;

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
public class DefaultProductService implements ProductService {

    private final ProductDao productDao;
    private final CategoryDao categoryDao;
    private final ProductRepository productRepository;

    public DefaultProductService(ProductDao productDao,
                                 CategoryDao categoryDao,
                                 ProductRepository productRepository) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getListOfProducts(Integer categoryId) {
        CategoryEntity categoryById = categoryDao.getCategoryById(categoryId);
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
    public Product getProductById(Integer id) {
        ProductEntity productEntity = productDao.findProduct(id);
        Category category = new Category(productEntity.getCategoryEntity().getId()
                , productEntity.getCategoryEntity().getCategoryName());

        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                category,
                productEntity.getImage(),
                productEntity.getAvailableAmount());
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.deleteProductById(id);

    }

    @Override
    public void addNewProduct(ProductEntity productEntity) {
        productRepository.save(productEntity);
    }

    @Override
    public void updateProductChanges(ProductEntity productEntity) {
        productRepository.save(productEntity);
    }


}
