package pl.sda.pol122.auctionservice.model;

import lombok.Getter;

@Getter
public enum Category {

    SPORT("sport"),
    HOUSE("house"),
    ELECTRONIC("electronic");

    private final String pluralValue;

    Category(String pluralValue) {
        this.pluralValue = pluralValue;
    }


}
