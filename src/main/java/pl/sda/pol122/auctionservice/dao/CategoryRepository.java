package pl.sda.pol122.auctionservice.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sda.pol122.auctionservice.entities.CategoryEntity;

@Repository
public interface CategoryRepository extends ListCrudRepository<CategoryEntity, Integer> {

   CategoryEntity findCategoryEntityById(Integer categoryId);

   @Transactional
   @Modifying
   @Query(value = "UPDATE category c SET c.enabled = :enabled WHERE c.category_id= :id" , nativeQuery = true)
   void setCategoryAvailability(@Param("enabled") Boolean setEnabledAccount, @Param("id") Integer id);



}
