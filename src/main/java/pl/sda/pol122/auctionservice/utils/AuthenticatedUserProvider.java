package pl.sda.pol122.auctionservice.utils;


import pl.sda.pol122.auctionservice.config.AuthenticatedUser;
import pl.sda.pol122.auctionservice.entities.UserEntity;
import pl.sda.pol122.auctionservice.enums.ERole;

public class AuthenticatedUserProvider {

    public static boolean checkIfLoggedUserIsAdmin() {
        return getLoggedUser().getRoles().contains(ERole.ADMIN);
    }

    private static UserEntity getLoggedUser() {
        return BeanProvider.getBean(AuthenticatedUser.class).get().orElse(null);
    }
}
