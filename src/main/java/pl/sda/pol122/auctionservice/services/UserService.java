package pl.sda.pol122.auctionservice.services;

import pl.sda.pol122.auctionservice.entities.UserEntity;


public interface UserService {

    UserEntity getUserById(Integer id);

    void deleteById(Integer id);

    void saveAccountStatusByAdmin(UserEntity userEntity);
    void saveAccountChangesByUser(UserEntity userEntity);
}
