package pl.sda.pol122.auctionservice.model;

import lombok.Builder;
import pl.sda.pol122.auctionservice.utils.validators.CCVConstraint;
import pl.sda.pol122.auctionservice.utils.validators.CardNumberConstraint;

@Builder
public class SubmitPayment {

    String firstName;
    String lastName;
    String street;
    String city;
    String state;
    String zipCode;

    String nameOnCard;
    @CardNumberConstraint
    String cardNumber;
    @CCVConstraint
    String ccv;


}
