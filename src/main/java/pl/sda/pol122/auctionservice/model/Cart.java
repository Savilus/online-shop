package pl.sda.pol122.auctionservice.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import pl.sda.pol122.auctionservice.entities.OrderItemEntity;

import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    private List<OrderItemEntity> orderedProductsByUser;
}
