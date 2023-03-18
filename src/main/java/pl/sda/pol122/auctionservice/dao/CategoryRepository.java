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
   @Query(value = "DELETE FROM category where category_id = :categoryId" , nativeQuery = true)
   void deleteCategoryEntitiesById(@Param("categoryId") Integer categoryId);



}
