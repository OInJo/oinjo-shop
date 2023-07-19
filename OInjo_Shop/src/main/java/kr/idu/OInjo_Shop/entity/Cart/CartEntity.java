package kr.idu.OInjo_Shop.entity.Cart;

import kr.idu.OInjo_Shop.dto.Cart.CartDTO;
import kr.idu.OInjo_Shop.dto.Cart.CartItemDTO;
import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int count; // 카트에 담긴 상품 개수

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private MemberEntity member;  //장바구니 사용자

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart")
    private final List<CartItemEntity> cartItems = new ArrayList<>();    //상품들

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate; // 날짜

    @PrePersist
    public void createDate() {
        this.createDate = LocalDate.now();
    }

    public static CartEntity creatCart(MemberEntity member) {
        CartEntity cart = new CartEntity();
        cart.setMember(member);
        cart.count = 0;

        return cart;
    }


}
