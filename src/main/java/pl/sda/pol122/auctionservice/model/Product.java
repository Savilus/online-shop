package pl.sda.pol122.auctionservice.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {


    private int id;
    private String name;
    private BigDecimal price;

    private Category category;

    private String image;

    private Integer availableAmount;

    private Boolean enabled;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(category, product.category) && Objects.equals(image, product.image) && Objects.equals(availableAmount, product.availableAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category, image, availableAmount);
    }
}
