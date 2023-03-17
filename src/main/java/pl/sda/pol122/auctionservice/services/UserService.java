package pl.sda.pol122.auctionservice.services;

import pl.sda.pol122.auctionservice.entities.UserEntity;
import pl.sda.pol122.auctionservice.model.Order;
import pl.sda.pol122.auctionservice.model.User;
import pl.sda.pol122.auctionservice.model.UserAddress;

import java.util.List;


public interface UserService {

    UserEntity getUserById(Integer id);

    void deleteById(Integer id);

    void saveAccountStatus(Integer userId, boolean accountStatus);

    void saveAccountChanges(User user, UserAddress userAddress);

    void createUserAccount(User user);
    void createAdminAccount(User user);

    User getAuthenticatedUser();

    List<Order> getUserOrderHistory();



}

