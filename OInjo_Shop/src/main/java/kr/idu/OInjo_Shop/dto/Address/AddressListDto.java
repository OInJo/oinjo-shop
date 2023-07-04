package kr.idu.OInjo_Shop.dto.Address;

import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class AddressListDto {
    public AddressListDto(MemberEntity member) {
        this.memberId = member.getId();
        this.name = member.getMemberName();
        this.email = member.getMemberEmail();
    }

    private Long memberId;  //멤버 아이디

    private String name;

    private String email;

    private List<AddressDto> addressList = new ArrayList<>();

    public void addAddressDto(AddressDto addressDto) {
        addressList.add(addressDto);
    }
}
