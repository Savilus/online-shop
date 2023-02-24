package pl.sda.pol122.auctionservice.model;

import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.sda.pol122.auctionservice.utils.validators.EmailConstraint;
import pl.sda.pol122.auctionservice.utils.validators.PasswordConstraint;
import pl.sda.pol122.auctionservice.utils.validators.UserNameConstraint;

@Getter
@Setter
@Builder
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

    public User(Integer id,
                String userName,
                String password,
                String firstName,
                String lastName,
                String email,
                Boolean enabled) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enabled = enabled;
    }

    public User() {
    }
}
