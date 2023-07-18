package kr.idu.OInjo_Shop.dto.Order;

import kr.idu.OInjo_Shop.constant.OrderStatus;
import kr.idu.OInjo_Shop.entity.Order.OrderEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class OrderHistDto {         //
    public OrderHistDto(OrderEntity order) {        //생성자로 값 변환
        this.orderId = order.getId();
        this.orderDate =
                order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.orderStatus = order.getOrderStatus();
        this.totalPrice = order.getTotalPrice();
    }
    private Long orderId;

    private String orderDate;   //주문 날짜

    private OrderStatus orderStatus;
    private int totalPrice;

    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

    public void addOrderItemDto(OrderItemDto orderItemDto) {
        orderItemDtoList.add(orderItemDto);     //orderItemDto 객체를 주문 상품 리스트에 추가
    }

}
