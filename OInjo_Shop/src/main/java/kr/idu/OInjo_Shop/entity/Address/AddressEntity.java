package kr.idu.OInjo_Shop.entity.Address;

import kr.idu.OInjo_Shop.dto.Address.AddressDto;
import kr.idu.OInjo_Shop.entity.BaseEntity;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Getter
@Setter
@ToString
public class AddressEntity extends BaseEntity {
    @Id
    @Column(name="address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private MemberEntity member;    //한번의 주문은 여러 주문 상품을 주문할 수 있으므로
    //주문 상품 엔티티와 주문 엔티티를 다대일 단방향 매핑을 먼저 설정함.

    @Column(nullable = false)
    private String address;


    public static AddressEntity createAddress(MemberEntity member, AddressDto addressDto) {
        return AddressEntity.builder()
                .member(member)
                .address(addressDto.getAddress())
                .build();
    }
    public void updateForm(AddressDto addressDto) {
        this.address = addressDto.getAddress();                 //this를 이용

    }

}
