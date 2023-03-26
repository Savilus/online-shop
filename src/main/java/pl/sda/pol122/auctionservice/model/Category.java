package pl.sda.pol122.auctionservice.model;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    private int id;

    private String categoryName;

    private String image;

    private Boolean enabled;



}
