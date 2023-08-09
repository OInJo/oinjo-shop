package kr.idu.OInjo_Shop.dto.Cart;

import kr.idu.OInjo_Shop.entity.Cart.CartEntity;
import kr.idu.OInjo_Shop.entity.Cart.CartItemEntity;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static kr.idu.OInjo_Shop.dto.Cart.CartItemDTO.toCartItemDTO;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDTO {
    private Long id;
    private int count;
    private MemberEntity member;  //장바구니 사용자
    private List<CartItemDTO> cartItems;   //상품들

    private LocalDate createDate; // 날짜


    public static CartEntity toCartEntity(CartDTO cartDTO) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setId(cartDTO.getId());
        cartEntity.setCount(cartDTO.getCount());
        cartEntity.setMember(cartDTO.getMember());
        cartEntity.setCreateDate(cartDTO.getCreateDate());

        return cartEntity;
    }


    public static CartDTO toCartDTO(CartEntity cartEntity) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cartEntity.getId());
        cartDTO.setCount(cartEntity.getCount());
        cartDTO.setMember(cartEntity.getMember());
        cartDTO.setCartItems(toCartItemDTOList(cartEntity.getCartItems()));
        cartDTO.setCreateDate(cartEntity.getCreateDate());

        return cartDTO;
    }

    private static List<CartItemDTO> toCartItemDTOList(List<CartItemEntity> cartItemEntities) {
        List<CartItemDTO> cartItemDTOList = new ArrayList<>();
        for (CartItemEntity cartItemEntity : cartItemEntities) {
            CartItemDTO cartItemDTO = toCartItemDTO(cartItemEntity);
            cartItemDTOList.add(cartItemDTO);
        }
        return cartItemDTOList;
    }



}
