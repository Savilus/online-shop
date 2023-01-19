package pl.sda.pol122.auctionservice.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "purchase")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
    private ProductEntity product;

    @Column(name = "amount", length = 3, nullable = false)
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity orderEntity;
}
