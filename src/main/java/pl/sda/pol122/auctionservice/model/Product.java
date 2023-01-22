package pl.sda.pol122.auctionservice.model;

import java.math.BigDecimal;

public class Product {


    private int id;
    private String name;
    private BigDecimal price;

    private Category category;

    private String image;

    public Product(int id, String name, BigDecimal price, Category category, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.image = image;
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

    public String getImage() {
        return image;
    }
}
