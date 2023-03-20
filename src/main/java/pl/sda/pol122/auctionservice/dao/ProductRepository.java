package pl.sda.pol122.auctionservice.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sda.pol122.auctionservice.entities.ProductEntity;

import java.util.List;

@Repository
public interface ProductRepository extends ListCrudRepository<ProductEntity, Integer> {

    ProductEntity findProductEntityById(Integer productId);

    void deleteProductById(Integer productId);
    @Query( value = "SELECT p.id, p.image, p.name, p.price, p.category_id, p.order_id, p.enabled, p.amount FROM category c\n" +
            "INNER JOIN product p\n" +
            "ON p.category_id=c.category_id\n" +
            "WHERE c.enabled =1 AND p.enabled = 1\n" +
            "ORDER BY RAND() LIMIT 6;",
    nativeQuery = true)
    List<ProductEntity> findRandomProductsById();

    @Modifying
    @Query(value = "UPDATE product p SET p.amount = :amount WHERE p.id= :id",
            nativeQuery = true)
    void updateAvailableAmountOfProducts(@Param("amount") Integer setAmount, @Param("id") Integer id);


    @Modifying
    @Query(value = "UPDATE product p SET p.enabled = :enabled WHERE p.id= :id" , nativeQuery = true)
    void setProductAvailability(@Param("enabled") Boolean setEnabledProduct, @Param("id") Integer id);

}
