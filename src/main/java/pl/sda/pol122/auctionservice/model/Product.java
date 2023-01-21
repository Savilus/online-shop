package pl.sda.pol122.auctionservice.model;

import java.math.BigDecimal;

public class Product {


    private int id;
    private String name;
    private BigDecimal price;

    private Category category;

    public Product(int id, String name, BigDecimal price, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
}
