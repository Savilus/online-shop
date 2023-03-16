package pl.sda.pol122.auctionservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

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
    @ColumnDefault(value = "")
    private String street;

    @Column(name = "building_number", length = 5)
    @ColumnDefault(value = "")
    private String buildingNumber;

    @Column(name = "flat_number", length = 5)
    @ColumnDefault(value = "")
    private String flatNumber;

    @Column(name = "city", length = 30)
    @ColumnDefault(value = "")
    private String city;

    @Column(name = "post_code", length = 6)
    @ColumnDefault(value = "")
    private String postCode;

    @OneToOne(mappedBy = "address")
    @ColumnDefault(value = "")
    private UserEntity user;
}
