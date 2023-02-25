package pl.sda.pol122.auctionservice.model;

import lombok.Builder;

@Builder
public class SubmitPayment {

    String firstName;
    String lastName;
    String street;
    String city;
    String state;
    String zipCode;

    String nameOnCard;
    String cardNumber;
    Integer ccv;


}
