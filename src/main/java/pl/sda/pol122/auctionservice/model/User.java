package pl.sda.pol122.auctionservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {

    private Integer id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
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
