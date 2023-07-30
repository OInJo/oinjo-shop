package kr.idu.OInjo_Shop.entity.Cart;

import kr.idu.OInjo_Shop.dto.Cart.CartItemDTO;
import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.entity.Item.ItemEntity;
import kr.idu.OInjo_Shop.entity.Item.ItemImgEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="cart_item")
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cart_id")
    private CartEntity cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="itemId")
    private ItemEntity product;

    private Integer count; // 카트에 담긴 상품 개수

    @ManyToOne
    @JoinColumn(name = "image_id") // 이미지 정보를 담는 외래키
    private ItemImgEntity image;


    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate; // 날짜

    @PrePersist // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    public void createDate() {
        this.createDate = LocalDate.now();
    }

    public static CartItemEntity createCartItem(CartEntity cart, ItemEntity item, int count){
        System.out.println(cart);
        System.out.println(item);
        System.out.println(count);
        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setCart(cart);
        cartItem.setProduct(item);
        cartItem.setCount(count);

        return cartItem;
    }
    public void addCount(int count){
        this.count += count;
    }
}
