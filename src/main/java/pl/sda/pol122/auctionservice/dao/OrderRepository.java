package pl.sda.pol122.auctionservice.dao;

import org.springframework.data.repository.ListCrudRepository;
import pl.sda.pol122.auctionservice.entities.OrderEntity;

import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {

    OrderEntity findOrderById(Integer orderId);

    List<OrderEntity> findAllByBuyerUserId(Integer userId);

}
