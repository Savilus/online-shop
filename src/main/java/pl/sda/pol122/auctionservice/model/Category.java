package pl.sda.pol122.auctionservice.model;

import lombok.Builder;

@Builder
public class Category {

    private int id;

    private String categoryName;

    private String image;

    public Category(int id, String categoryName, String photo) {
        this.id = id;
        this.categoryName = categoryName;
        this.image = photo;
    }

    public Category(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getImage() {
        return image;
    }
}
