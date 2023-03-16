package pl.sda.pol122.auctionservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserAddress {

    private String street;

    private String buildingNumber;

    private String flatNumber;

    private String city;

    private String postCode;

    public UserAddress(String street, String buildingNumber, String flatNumber, String city, String postCode) {
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.flatNumber = flatNumber;
        this.city = city;
        this.postCode = postCode;
    }

    public UserAddress() {
    }
}
