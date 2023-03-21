package pl.sda.pol122.auctionservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Builder
@Getter
@Setter
public class Product {


    private int id;
    private String name;
    private BigDecimal price;

    private Category category;

    private String image;

    private Integer availableAmount;

    private Boolean enabled;

    public Product(int id, String name, BigDecimal price, Category category, String image, Integer availableAmount, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.image = image;
        this.availableAmount = availableAmount;
        this.enabled = enabled;
    }

    public Product() {
    }

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
