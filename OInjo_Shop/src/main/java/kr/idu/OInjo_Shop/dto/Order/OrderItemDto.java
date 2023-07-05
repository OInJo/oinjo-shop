package kr.idu.OInjo_Shop.dto.Order;

import kr.idu.OInjo_Shop.entity.Order.OrderItemEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderItemDto {
    private String itemNm;
    private int count;



    public OrderItemDto(OrderItemEntity orderItem) {
//        this.itemNm = orderItem

    }
}
