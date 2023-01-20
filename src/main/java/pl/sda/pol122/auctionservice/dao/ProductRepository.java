package pl.sda.pol122.auctionservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.pol122.auctionservice.entities.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    ProductEntity findProductEntityById(String productId);

    void deleteById(String productId);

}
