package pl.sda.pol122.auctionservice.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {

    private Integer id;
    @NotEmpty
    @NotNull
    private String userName;
    @NotNull
    @NotEmpty
    private String password;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
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
