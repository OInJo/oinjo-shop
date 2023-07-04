package kr.idu.OInjo_Shop.repository.Order;

import kr.idu.OInjo_Shop.entity.Order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
