package pl.sda.pol122.auctionservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Builder
@Getter
@AllArgsConstructor
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value")
    private BigDecimal valueOfOrder;

    @OneToMany(mappedBy = "orderEntity")
    private List<OrderItemEntity> listOfProducts;

    @Column(name = "userId")
    private Integer buyerUserId;

    public OrderEntity() {
    }
}
