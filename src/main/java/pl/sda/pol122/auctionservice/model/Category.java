package pl.sda.pol122.auctionservice.model;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class Category {

    private int id;

    private String categoryName;

    private String image;

    public Category() {
    }

    public Category(int id, String categoryName, String image) {
        this.id = id;
        this.categoryName = categoryName;
        this.image = image;
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
