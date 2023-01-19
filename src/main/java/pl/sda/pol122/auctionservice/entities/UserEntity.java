package pl.sda.pol122.auctionservice.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name", length = 30, nullable = false)
    private String userName;

    @Column(name = "password", length = 30, nullable = false)
    private String password;

    @Column(name = "first_name", length = 30, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @Column(name = "account_status", nullable = false)
    private Boolean isActive;

    @ManyToMany
    @JoinTable(name = "user_addresses",
    joinColumns = @JoinColumn(name = "address_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserAddressEntity> userAddresses;






}
