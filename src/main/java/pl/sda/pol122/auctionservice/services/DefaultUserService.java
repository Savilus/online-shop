package pl.sda.pol122.auctionservice.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import pl.sda.pol122.auctionservice.controllers.validators.SignUpValidator;
import pl.sda.pol122.auctionservice.dao.UserRepository;
import pl.sda.pol122.auctionservice.entities.UserEntity;
import pl.sda.pol122.auctionservice.model.User;

import java.util.List;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final SignUpValidator signUpValidator;

    public DefaultUserService(UserRepository userRepository, SignUpValidator signUpValidator) {
        this.userRepository = userRepository;
        this.signUpValidator = signUpValidator;
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

    public List<ObjectError> validatePasswordAndLogin(User user) {
        DataBinder dataBinder = new DataBinder(user);
        dataBinder.addValidators(signUpValidator);
        dataBinder.validate();
        List<ObjectError> allErrors = dataBinder.getBindingResult().getAllErrors();
        return allErrors;
    }

    @Override
    public void saveAccountStatus(Integer userId, boolean accountStatus) {
        UserEntity userEntityById = userRepository.getUserEntityById(userId);
        userEntityById.setEnabled(accountStatus);
        userRepository.save(userEntityById);
    }

    @Override
    public void saveAccountChanges(User user) {
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
