package kr.idu.OInjo_Shop.dto.Order;

import kr.idu.OInjo_Shop.entity.Order.OrderItemEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderItemDto {     //조회한 주문 데이터를 화면에 보낼 때 사용


    public OrderItemDto(OrderItemEntity orderItem,String imgUrl) {
        this.itemNm = orderItem.getItem().getItemName();
        this.count = orderItem.getCount();
        this.orderPrice = orderItem.getOrderPrice();
        this.imgUrl = imgUrl;
    }
    private String itemNm;
    private int count;
    private int orderPrice;
    private String imgUrl;

}
