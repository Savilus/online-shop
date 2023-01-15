package pl.sda.pol122.auctionservice.model;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Product {

    String id;

    String name;

    Category category;

    Integer amount;
    BigDecimal price;


}
