package pl.sda.pol122.auctionservice.services;

import pl.sda.pol122.auctionservice.model.User;

import java.util.List;

public interface AdminService {

    void banOrUnbanUser(Integer id);

    List<User> listOfUsers();
}
