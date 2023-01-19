package pl.sda.pol122.auctionservice.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value")
    private BigDecimal valueOfOrder;

    @OneToMany(mappedBy = "orderEntity")
    private List<OrderItemEntity> listOfProducts;

    @OneToOne
    @JoinColumn(name = "sellerUserEntity", referencedColumnName = "id")
    private UserEntity sellerUserEntity;

    @OneToOne
    @JoinColumn(name = "buyerUserEntity", referencedColumnName = "id")
    private UserEntity buyerUserEntity;


}
