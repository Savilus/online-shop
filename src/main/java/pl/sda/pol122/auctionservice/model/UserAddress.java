package pl.sda.pol122.auctionservice.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {

    private String street;

    private String buildingNumber;

    private String flatNumber;

    private String city;

    private String postCode;

}
