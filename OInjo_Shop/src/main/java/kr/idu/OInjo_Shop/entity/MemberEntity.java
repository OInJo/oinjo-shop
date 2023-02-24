package kr.idu.OInjo_Shop.entity;

import kr.idu.OInjo_Shop.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "member_table")
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

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberNickname(memberDTO.getMemberNickname());
        memberEntity.setMemberPhone(memberDTO.getMemberPhone());
        memberEntity.setMemberAddress(memberDTO.getMemberAddress());
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
}
