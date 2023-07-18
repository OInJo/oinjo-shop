package kr.idu.OInjo_Shop.dto.Order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDto {         // 데이터 전송에 필요한 값만을 받아서 Dto에 담는다.
                                // 불필요한 데이터를 담을 경우 보안상의 문제가 있을 수 있고,
                                // 용량이 늘어나 네트워크 전송 비용이 늘어날 수 있다.
    private Long itemId;        //상품 아이디

    private int count;

}
