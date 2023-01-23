package pl.sda.pol122.auctionservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.pol122.auctionservice.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity getUserEntityById(Integer id);
    void deleteById(Integer id);

}
