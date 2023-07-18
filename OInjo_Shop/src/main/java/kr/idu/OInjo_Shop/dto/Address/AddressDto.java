package kr.idu.OInjo_Shop.dto.Address;

import kr.idu.OInjo_Shop.entity.Address.AddressEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

@Getter@Setter@ToString
public class AddressDto {
    private Long addressId;
    private String address;



    public AddressDto() {
    }
    public AddressDto(AddressEntity addressEntity) {        //생성자 사용

        this.addressId = addressEntity.getId();
        this.address= addressEntity.getAddress();

    }


    public static AddressDto of(AddressEntity address) {        //정적 팩토리 메서드 사용
        AddressDto addressDto = new AddressDto();
        addressDto.setAddressId(address.getId());
        addressDto.setAddress(address.getAddress());
        return addressDto;
    }


}
