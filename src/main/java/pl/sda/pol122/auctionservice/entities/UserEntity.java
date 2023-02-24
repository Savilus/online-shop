package pl.sda.pol122.auctionservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.sda.pol122.auctionservice.enums.ERole;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", length = 30, nullable = false)
    private String login;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "firstname", length = 30, nullable = false)
    private String firstName;

    @Column(name = "lastname", length = 45, nullable = false)
    private String lastName;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @ManyToMany
    @JoinTable(name = "user_addresses",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserAddressEntity> userAddresses;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles")
    private Set<ERole> roles;

    public UserEntity() {

    }
}
