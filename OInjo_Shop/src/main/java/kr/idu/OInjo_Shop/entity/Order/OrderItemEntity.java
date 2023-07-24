package kr.idu.OInjo_Shop.entity.Order;

import kr.idu.OInjo_Shop.entity.BaseEntity;
import kr.idu.OInjo_Shop.entity.Item.ItemEntity;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "order_item")
public class OrderItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    private int orderPrice;

    private int count;          //주문 수량

    public static OrderItemEntity createOrderItem(ItemEntity item, int count) {
        OrderItemEntity orderItem = new OrderItemEntity();
        orderItem.setItem(item);
        orderItem.setCount(count);
        orderItem.setOrderPrice(item.getItemPrice());


        item.decreaseItem(count);
        return orderItem;
    }

    public int getTotalPrice() {
        return orderPrice*count;
    }

    public void cancel() {    
        this.getItem().increaseItem(count);
    }
}
