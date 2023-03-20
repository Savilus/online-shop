package pl.sda.pol122.auctionservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Category {

    private int id;

    private String categoryName;

    private String image;

    private Boolean enabled;

    public Category() {
    }

    public Category(int id, String categoryName, String image, Boolean enabled) {
        this.id = id;
        this.categoryName = categoryName;
        this.image = image;
        this.enabled = enabled;
    }


}
