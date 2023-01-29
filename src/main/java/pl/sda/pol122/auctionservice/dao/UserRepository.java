package pl.sda.pol122.auctionservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.sda.pol122.auctionservice.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity getUserEntityById(Integer id);
    void deleteById(Integer id);

    @Query(value = "SELECT EXISTS(SELECT * FROM users WHERE username = :userName)",
            nativeQuery = true )
    Integer checkIfUsernameIsAvailable(@Param("userName") String userName);

}
