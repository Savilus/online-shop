package pl.sda.pol122.auctionservice.dao;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.pol122.auctionservice.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    OrderEntity findOrderById(Integer orderId);

}
