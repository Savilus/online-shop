package pl.sda.pol122.auctionservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sda.pol122.auctionservice.entities.ProductEntity;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    ProductEntity findProductEntityById(Integer productId);

    void deleteProductById(Integer productId);
    @Query( value = "SELECT * FROM product ORDER BY RAND() LIMIT 6",
    nativeQuery = true)
    List<ProductEntity> findRandomProductsById();


}
