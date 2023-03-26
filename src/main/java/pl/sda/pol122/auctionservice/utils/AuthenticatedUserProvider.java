package pl.sda.pol122.auctionservice.utils;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import pl.sda.pol122.auctionservice.config.AuthenticatedUser;
import pl.sda.pol122.auctionservice.entities.UserEntity;


@Component
public record AuthenticatedUserProvider(AuthenticatedUser authenticatedUser) {

    public static UserEntity getLoggedUser() {

        return AuthenticatedUser.get().orElse(null);
    }

    public boolean checkIfLoggedUserIsAdmin() {
        return authenticatedUser.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
    }

    public boolean checkIfLoggedUserIsSuperAdmin() {
        return authenticatedUser.getAuthorities().contains(new SimpleGrantedAuthority("SUPER_ADMIN"));
    }



}
