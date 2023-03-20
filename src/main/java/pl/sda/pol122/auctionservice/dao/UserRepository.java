package pl.sda.pol122.auctionservice.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying

    @Query(value = "UPDATE users u SET u.enabled = :enabled WHERE u.id= :id",
            nativeQuery = true)
    void banOrUnbanUser(@Param("enabled") Boolean setEnabledAccount, @Param("id") Integer id);

    @Query(value = "SELECT username FROM authorities WHERE authority != 'SUPER_ADMIN'",
            nativeQuery = true)
    List<String> getUserAndAdminsFromDB();

    @Query(value = "SELECT authority FROM authorities WHERE username = :username",
            nativeQuery = true)
    String getAuthorityByUsername(@Param("username") String userName);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM authorities where username = :username" , nativeQuery = true)
    void deleteAuthorityByUserName(@Param("username") String username);
}

