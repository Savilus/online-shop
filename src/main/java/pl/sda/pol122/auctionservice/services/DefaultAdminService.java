package pl.sda.pol122.auctionservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.pol122.auctionservice.dao.UserRepository;
import pl.sda.pol122.auctionservice.entities.UserEntity;
import pl.sda.pol122.auctionservice.model.User;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultAdminService implements AdminService{

    private final UserRepository userRepository;




    @Override
    public void banOrUnbanUser(Integer id) {
        UserEntity userById = userRepository.getUserEntityById(id);
        userRepository.banOrUnbanUser(!userById.getEnabled(), id);
    }

    @Override
    public List<User> listOfUsers() {
        List<String> userAndAdminsFromDB = userRepository.getUserAndAdminsFromDB();

        return userAndAdminsFromDB.stream().map(userRepository::findByLogin)
                .map(userEntity -> User.builder()
                        .id(userEntity.getId())
                        .firstName(userEntity.getFirstName())
                        .lastName(userEntity.getLastName())
                        .password(userEntity.getPassword())
                        .email(userEntity.getEmail())
                        .enabled(userEntity.getEnabled())
                        .userName(userEntity.getLogin())
                        .build())
                .toList();
    }
}
