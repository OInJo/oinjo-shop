package kr.idu.OInjo_Shop.repository.Order;

import kr.idu.OInjo_Shop.entity.Order.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}
