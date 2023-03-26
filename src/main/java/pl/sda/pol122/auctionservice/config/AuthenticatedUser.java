package pl.sda.pol122.auctionservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.sda.pol122.auctionservice.dao.UserRepository;
import pl.sda.pol122.auctionservice.entities.UserEntity;

import java.util.Collection;
import java.util.Optional;

@Component
public class AuthenticatedUser {


    private static UserRepository userRepository;

    @Autowired
    public AuthenticatedUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




    private static Optional<Authentication> getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        return Optional.ofNullable(context.getAuthentication())
                .filter(authentication -> !(authentication instanceof AnonymousAuthenticationToken));
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Authentication authentication = getAuthentication().get();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities;
    }

    public static Optional<UserEntity> get() {
        return getAuthentication().map(authentication ->
                userRepository.findByLogin(authentication.getName()));
    }


}
