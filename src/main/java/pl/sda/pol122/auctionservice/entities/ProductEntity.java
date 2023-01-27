package pl.sda.pol122.auctionservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Builder
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 135, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity categoryEntity;

    @Column(name = "amount", nullable = false)
    private Integer availableAmount;
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "image", nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    public ProductEntity() {

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public Integer getAvailableAmount() {
        return availableAmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public String getImage() {
        return image;
    }
}
