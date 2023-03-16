package pl.sda.pol122.auctionservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
public class UserAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "street", length = 30)
    private String street;

    @Column(name = "building_number", length = 5)
    private String buildingNumber;

    @Column(name = "flat_number", length = 5)
    private String flatNumber;

    @Column(name = "city", length = 30)
    private String city;

    @Column(name = "post_code", length = 6)
    private String postCode;

    @OneToOne(mappedBy = "address")
    private UserEntity user;
}
