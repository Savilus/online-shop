package pl.sda.pol122.auctionservice.utils;


import pl.sda.pol122.auctionservice.config.AuthenticatedUser;
import pl.sda.pol122.auctionservice.entities.UserEntity;

public class AuthenticatedUserProvider {

    private static UserEntity getLoggedUser() {
        return BeanProvider.getBean(AuthenticatedUser.class).get().orElse(null);
    }
}
