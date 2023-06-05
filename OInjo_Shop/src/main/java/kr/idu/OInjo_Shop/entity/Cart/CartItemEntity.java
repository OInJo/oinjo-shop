package kr.idu.OInjo_Shop.entity.Cart;

import kr.idu.OInjo_Shop.dto.Cart.CartItemDTO;
import kr.idu.OInjo_Shop.entity.Item.ItemEntity;
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
    @Column(name="cart_items")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cart_id")
    private CartEntity cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private ItemEntity product;

    private int count; // 카트에 담긴 상품 개수


    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate; // 날짜

    @PrePersist // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    public void createDate() {
        this.createDate = LocalDate.now();
    }

    public void addCount(int count){
        this.count += count;
    }
}
