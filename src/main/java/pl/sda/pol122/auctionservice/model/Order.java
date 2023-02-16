package pl.sda.pol122.auctionservice.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Builder
@Data
public class Order {

    private Integer orderId;

    private BigDecimal valueOfOrder;

    Map<Product,Integer> productQuantityMap;
}
