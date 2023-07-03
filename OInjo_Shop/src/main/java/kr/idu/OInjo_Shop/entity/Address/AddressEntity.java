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
@Table(name="address")
@Getter
@Setter
@ToString
public class AddressEntity extends BaseEntity {
    @Id
    @Column(name="address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

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
