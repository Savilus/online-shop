package pl.sda.pol122.auctionservice.dao;

import pl.sda.pol122.auctionservice.model.Category;

import java.math.BigDecimal;

public interface ProductRepository {

    String getId();

    Category getCategory();

    String getName();

    Integer getAmount();

    BigDecimal getPrice();
}
