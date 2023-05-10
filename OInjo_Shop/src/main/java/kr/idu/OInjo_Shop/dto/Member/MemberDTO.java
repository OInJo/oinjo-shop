package kr.idu.OInjo_Shop.dto.Member;

import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberNickname;
    private String memberPhone;
    private String memberAddress;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberNickname(memberEntity.getMemberNickname());
        memberDTO.setMemberPhone(memberEntity.getMemberPhone());
        memberDTO.setMemberAddress(memberEntity.getMemberAddress());
        return memberDTO;
    }
}
