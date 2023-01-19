package pl.sda.pol122.auctionservice.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user_address")
public class UserAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "street", length = 30, nullable = false)
    private String street;

    @Column(name = "building_number", length = 5, nullable = false)
    private String buildingNumber;

    @Column(name = "flat_number", length = 5)
    private String flatNumber;

    @Column(name = "city", length = 30, nullable = false)
    private String city;

    @Column(name = "post_code", length = 6, nullable = false)
    private String postCode;

    @ManyToMany(mappedBy = "userAddresses")
    private List<UserEntity> users;
}
