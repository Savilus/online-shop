package pl.sda.pol122.auctionservice.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.sda.pol122.auctionservice.dao.UserRepository;
import pl.sda.pol122.auctionservice.entities.UserEntity;
import pl.sda.pol122.auctionservice.model.User;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultAdminService implements AdminService {

    private final UserRepository userRepository;




    @Transactional
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

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteAccount(String username) {
        String userToDelete = userRepository.getAuthorityByUsername(username);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> loggedInAccountAuthority =  authentication.getAuthorities();

        if(loggedInAccountAuthority.contains(new SimpleGrantedAuthority("SUPER_ADMIN"))){
            Integer everyAccountId = userRepository.findByLogin(username).getId();
            userRepository.deleteById(everyAccountId);
            userRepository.deleteAuthorityByUserName(username);


        } else {
            if(userToDelete.equals("USER")){
                Integer userAccountId = userRepository.findByLogin(username).getId();
                userRepository.deleteById(userAccountId);
                userRepository.deleteAuthorityByUserName(username);
            }
        }



    }


}


