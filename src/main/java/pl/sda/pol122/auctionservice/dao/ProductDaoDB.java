package pl.sda.pol122.auctionservice.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.sda.pol122.auctionservice.entities.ProductEntity;

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
    public ProductEntity findProduct(String productId) {
        return productRepository.findProductEntityById(productId);
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Page<ProductEntity> findProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
