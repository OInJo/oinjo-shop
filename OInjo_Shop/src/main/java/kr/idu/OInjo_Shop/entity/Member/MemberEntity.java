package kr.idu.OInjo_Shop.entity.Member;

import kr.idu.OInjo_Shop.dto.Address.AddressDto;
import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.entity.Address.AddressEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_table")
@SequenceGenerator(sequenceName = "member_seq", name = "member_seq_gen", initialValue = 1, allocationSize = 1)
public class MemberEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(unique = true) // unique 제약조건 추가
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    @Column(unique = true)
    private String memberNickname;

    @Column
    private String memberPhone;

    @Column
    private String memberAddress;



    //cascade: member 부모 엔티티에 영속성 컨텍스트에 변화가 일어나면 address 자식 엔티티에도 변화가 일어나게 설정함.
    //orphanRemoval: 부모 엔티티와 연관 관계가 끊어진 자식 엔티티를 자동으로 삭제
    //CascadeType.REMOVE vs orphanRemoval => 부모 엔티티가 삭제될 때 자식도 삭제(member를 삭제하면 매핑된 address가 삭제), 연관 관계가 끊어지면 고아 객체가 되는데 이를 삭제
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<AddressEntity> addresses = new ArrayList<>();      //주소의 모음이기에 복수형으로 선언


    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberNickname(memberDTO.getMemberNickname());
        memberEntity.setMemberPhone(memberDTO.getMemberPhone());
        memberEntity.setMemberAddress(memberDTO.getMemberAddress());

        AddressEntity address = new AddressEntity();
        address.setAddress(memberDTO.getMemberAddress());       //멤버 엔티티에 address와 같은 값을 넣고
        memberEntity.addAddress(address);                       //그 값을 addresses에 추가함.
        return memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId()); // Id 쿼리도 같이 수행해야 에러 x
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberNickname(memberDTO.getMemberNickname());
        memberEntity.setMemberPhone(memberDTO.getMemberPhone());
        memberEntity.setMemberAddress(memberDTO.getMemberAddress());
        return memberEntity;
    }

    public void addAddress(AddressEntity address) {     //member와 address간의 양방향 설정을 해줌.
        addresses.add(address);         //Address 엔티티에 address를 member 엔티티에 addresses에 넣어줌
        address.setMember(this);
    }

    public void setAddressFromAddressList(AddressEntity address) {
        this.memberAddress=address.getAddress();
    }
}
