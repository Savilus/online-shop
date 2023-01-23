package pl.sda.pol122.auctionservice.model;

import java.math.BigDecimal;

public class Product {


    private int id;
    private String name;
    private BigDecimal price;

    private Category category;

    private String image;

    private Integer availableAmount;

    public Product(int id, String name, BigDecimal price, Category category, String image, Integer availableAmount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.image = image;
        this.availableAmount = availableAmount;
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

    public Integer getAvailableAmount() {
        return availableAmount;
    }
}
