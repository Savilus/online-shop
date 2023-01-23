package pl.sda.pol122.auctionservice.services;

import org.springframework.stereotype.Service;
import pl.sda.pol122.auctionservice.dao.UserRepository;
import pl.sda.pol122.auctionservice.entities.UserEntity;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getUserById(Integer id) {
        return userRepository.getUserEntityById(id);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void saveAccountStatusByAdmin(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public void saveAccountChangesByUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

}
