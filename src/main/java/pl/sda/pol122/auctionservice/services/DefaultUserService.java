package pl.sda.pol122.auctionservice.services;

import org.springframework.stereotype.Service;
import pl.sda.pol122.auctionservice.config.AuthenticatedUser;
import pl.sda.pol122.auctionservice.dao.UserRepository;
import pl.sda.pol122.auctionservice.entities.UserEntity;
import pl.sda.pol122.auctionservice.model.User;

import java.util.Optional;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    private final AuthenticatedUser authenticatedUser;

    public DefaultUserService(UserRepository userRepository, AuthenticatedUser authenticatedUser) {
        this.userRepository = userRepository;
        this.authenticatedUser = authenticatedUser;
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
    public void createUserAccount(User user) {
        UserEntity userEntity = UserEntity
                .builder()
                .login(user.getUserName())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .enabled(true)
                .build();
        userRepository.save(userEntity);
    }

    @Override
    public User getAuthenticatedUser() {
        Optional<UserEntity> userEntity = authenticatedUser.get();
        User user;
        if(userEntity.isPresent()){
            UserEntity existingEntityUser = userEntity.get();
            user = User.builder()
                    .id(existingEntityUser.getId())
                    .userName(existingEntityUser.getFirstName())
                    .lastName(existingEntityUser.getLastName())
                    .email(existingEntityUser.getEmail())
                    .build();


        }else {
            throw new RuntimeException("This user does not exist");
        }
        return user ;
    }

    @Override
    public void saveAccountStatusByAdmin(Integer userId, boolean accountStatus) {
        UserEntity userEntityById = userRepository.getUserEntityById(userId);
        userEntityById.setEnabled(accountStatus);
        userRepository.save(userEntityById);
    }

    @Override
    public void saveAccountChangesByUser(User user) {
        UserEntity userEntity = UserEntity
                .builder()
                .login(user.getUserName())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .enabled(user.getEnabled())
                .build();
        userRepository.save(userEntity);
    }

}
