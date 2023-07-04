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
    public AddressDto(AddressEntity addressEntity) {

        this.address= addressEntity.getAddress();
        this.addressId = addressEntity.getId();

    }


    public static AddressDto of(AddressEntity address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setAddressId(address.getId());
        addressDto.setAddress(address.getAddress());
        return addressDto;
    }


}
