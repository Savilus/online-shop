package pl.sda.pol122.auctionservice.dao;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.pol122.auctionservice.entities.CategoryEntity;

@Repository
public interface CategoryRepository extends ListCrudRepository<CategoryEntity, Integer> {

   CategoryEntity findCategoryEntityById(Integer categoryId);


}
