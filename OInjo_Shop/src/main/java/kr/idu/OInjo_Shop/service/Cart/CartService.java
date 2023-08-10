package kr.idu.OInjo_Shop.service.Cart;

import kr.idu.OInjo_Shop.dto.Cart.CartDTO;
import kr.idu.OInjo_Shop.dto.Cart.CartItemDTO;
import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.dto.Order.OrdersDto;


import java.util.List;

public interface CartService {

    // 장바구니 생성
    void createCart(MemberDTO member);

    // 장바구니에 상품 추가
    void addCart(MemberDTO member, ItemFormDTO item, Integer count);

    // 장바구니 조회하기
    List<CartItemDTO> memberCartView(CartDTO cart);

    // 장바구니 특정 상품 갯수 수정하기
    boolean updateItemCount(Long id, Long itemId, Integer count);

    // 장바구니 상품 삭제하기
    void cartItemDelete(Long id);

    // 장바구니의 모든 상품 삭제하기
    boolean allCartItemDelete(Long id);

    // 장바구니 삭제
    void cartDelete(Long id);

    // 장바구니에서 주문 생성하기
    Long cartOrders(List<OrdersDto> ordersDtoList, String email);
}
