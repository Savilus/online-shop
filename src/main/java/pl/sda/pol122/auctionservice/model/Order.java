package pl.sda.pol122.auctionservice.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Builder
@Data
public class Order {

    private Integer orderId;

    private BigDecimal valueOfOrder;

    private Map<Product,Integer> productQuantityMap;

    private String orderTimeStamp;
}
