package pl.sda.pol122.auctionservice.model;

import lombok.*;
import pl.sda.pol122.auctionservice.utils.validators.CCVConstraint;
import pl.sda.pol122.auctionservice.utils.validators.CardNumberConstraint;
import pl.sda.pol122.auctionservice.utils.validators.PostCodeConstraint;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubmitPayment {

    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;

    @PostCodeConstraint
    private String zipCode;

    private String nameOnCard;

    @CardNumberConstraint
    private String cardNumber;

    @CCVConstraint
    private String ccv;


}
