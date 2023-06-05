package kr.idu.OInjo_Shop.dto.Cart;

import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.entity.Cart.CartEntity;
import kr.idu.OInjo_Shop.entity.Cart.CartItemEntity;
import kr.idu.OInjo_Shop.entity.Item.ItemEntity;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItemDTO {
    private Long id;
    private CartEntity cart;
    private ItemEntity product;
    private int count;
    private LocalDate createDate;

    // Getters and setters

    public static CartItemEntity toCartItemEntity(CartDTO cartDTO, ItemFormDTO item, int count) {
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setId(cartDTO.getId());
        cartItemEntity.setCart(CartDTO.toCartEntity(cartDTO));
        cartItemEntity.setProduct(item.createItem());
        cartItemEntity.setCount(count);
        cartItemEntity.setCreateDate(cartDTO.getCreateDate());

        return cartItemEntity;
    }


    public static CartItemDTO toCartItemDTO(CartEntity cartEntity) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartEntity.getId());
        cartItemDTO.setCart(CartDTO.toCartEntity(CartDTO.toCartDTO(cartEntity)));
        cartItemDTO.setProduct((ItemEntity) cartEntity.getCartItems());
        cartItemDTO.setCount(cartEntity.getCount());
        cartItemDTO.setCreateDate(cartEntity.getCreateDate());

        return cartItemDTO;
    }

}
