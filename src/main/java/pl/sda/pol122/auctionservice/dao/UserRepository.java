package pl.sda.pol122.auctionservice.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import pl.sda.pol122.auctionservice.entities.UserEntity;

import java.util.List;

public interface UserRepository extends ListCrudRepository<UserEntity, Integer> {

    List<UserEntity> findAll();

    UserEntity getUserEntityById(Integer id);

    void deleteById(Integer id);

    UserEntity findByLogin(String userName);

    @Query(value = "SELECT EXISTS(SELECT * FROM users WHERE username = :userName)",
            nativeQuery = true)
    Integer checkIfUsernameIsAvailable(@Param("userName") String userName);

}
