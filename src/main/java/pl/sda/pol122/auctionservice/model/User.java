package pl.sda.pol122.auctionservice.model;

import lombok.*;
import pl.sda.pol122.auctionservice.utils.validators.EmailConstraint;
import pl.sda.pol122.auctionservice.utils.validators.PasswordConstraint;
import pl.sda.pol122.auctionservice.utils.validators.UserNameConstraint;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    @UserNameConstraint
    private String userName;
    @PasswordConstraint
    private String password;
    private String firstName;
    private String lastName;
    @EmailConstraint
    private String email;
    private Boolean enabled;

    private UserAddress userAddress;

}
