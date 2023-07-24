package kr.idu.OInjo_Shop.dto.Order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrdersDto {
    private Long cartItemId;

    private List<OrdersDto> OrdersDtoList;
}
