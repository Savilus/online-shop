package pl.sda.pol122.auctionservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.pol122.auctionservice.entities.OrderEntity;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    OrderEntity findOrderById(Integer orderId);

    List<OrderEntity> findAllByBuyerUserId(Integer userId);

}
