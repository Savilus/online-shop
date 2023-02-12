package pl.sda.pol122.auctionservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


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

    @ElementCollection
    @MapKeyColumn(name="product_id")
    @Column(name="quantity")
    @CollectionTable(name="products_of_order", joinColumns=@JoinColumn(name="id"))
    private Map<Integer, Integer> orderedProductsWithQuantity;

    @Column(name = "userId")
    private Integer buyerUserId;

    public OrderEntity() {
    }
}
