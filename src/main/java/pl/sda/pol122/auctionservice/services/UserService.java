package pl.sda.pol122.auctionservice.services;

import pl.sda.pol122.auctionservice.entities.UserEntity;
import pl.sda.pol122.auctionservice.model.User;


public interface UserService {

    UserEntity getUserById(Integer id);
    void deleteById(Integer id);
    void saveAccountStatusByAdmin(Integer userId, boolean accountStatus);
    void saveAccountChangesByUser(User user);
    void createUserAccount(User user);

}
