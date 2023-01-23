package pl.sda.pol122.auctionservice.dao;

import org.springframework.stereotype.Repository;
import pl.sda.pol122.auctionservice.entities.CategoryEntity;
import pl.sda.pol122.auctionservice.entities.ProductEntity;

import java.util.List;

@Repository
public class ProductDaoDB implements ProductDao {

    private final ProductRepository productRepository;

    public ProductDaoDB(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void saveProduct(ProductEntity product) {
        productRepository.save(product);
    }

    @Override
    public ProductEntity findProduct(Integer productId) {
        return productRepository.findProductEntityById(productId);
    }

    @Override
    public void deleteProduct(Integer productId) {
        productRepository.deleteProductById(productId);
    }

    @Override
    public List<ProductEntity> findProducts(Integer categoryId) {
        return productRepository.findAll();
    }

}
