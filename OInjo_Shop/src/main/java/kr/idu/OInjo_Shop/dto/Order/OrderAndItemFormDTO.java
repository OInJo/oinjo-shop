package kr.idu.OInjo_Shop.dto.Order;

import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderAndItemFormDTO {
    private OrderDto orderDto;
    private ItemFormDTO itemFormDTO;
}
