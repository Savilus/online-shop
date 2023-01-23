package pl.sda.pol122.auctionservice.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", length = 30, nullable = false)
    private String userName;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "firstname", length = 30, nullable = false)
    private String firstName;

    @Column(name = "lastname", length = 45, nullable = false)
    private String lastName;

    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @ManyToMany
    @JoinTable(name = "user_addresses",
    joinColumns = @JoinColumn(name = "address_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserAddressEntity> userAddresses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<UserAddressEntity> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(List<UserAddressEntity> userAddresses) {
        this.userAddresses = userAddresses;
    }
}
