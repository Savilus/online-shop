package pl.sda.pol122.auctionservice.services;

import org.springframework.validation.ObjectError;
import pl.sda.pol122.auctionservice.entities.UserEntity;
import pl.sda.pol122.auctionservice.model.User;

import java.util.List;


public interface UserService {

    UserEntity getUserById(Integer id);
    void deleteById(Integer id);
    void saveAccountStatus(Integer userId, boolean accountStatus);
    void saveAccountChanges(User user);
    void createUserAccount(User user);
    List<ObjectError> validatePasswordAndLogin(User user);
    User getAuthenticatedUser();


}

