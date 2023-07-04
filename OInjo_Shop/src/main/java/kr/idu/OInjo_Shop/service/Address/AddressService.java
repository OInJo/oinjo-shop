package kr.idu.OInjo_Shop.service.Address;


import kr.idu.OInjo_Shop.dto.Address.AddressDto;
import kr.idu.OInjo_Shop.dto.Address.AddressListDto;
import kr.idu.OInjo_Shop.entity.Address.AddressEntity;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import kr.idu.OInjo_Shop.repository.Address.AddressRepository;
import kr.idu.OInjo_Shop.repository.Member.MemberRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional      //로직을 처리하다가 에러가 발생하였다면, 변경된 데이터를 로직을 수행하기 이전 상태로 콜백 시켜줌.
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)     //데이터베이스 작업이 읽기 작업으로 처리되며, 데이터베이스에서 데이터를 수정하는 작업은 허용되지 않는다.
    //트랜잭션을 읽기 전용으로 사용하면 데이터의 일관성을 유지하면서 성능을 최적화할 수 있음
    public Page<AddressListDto> getAddressList(String email, Pageable pageable) {
        List<AddressEntity> addresses = addressRepository.findAddresses(email, pageable);
        Long totalCount = addressRepository.countAddress(email);
        List<AddressListDto> addressListDtos = new ArrayList<>();
        for (AddressEntity address : addresses) {
            AddressListDto addressListDto = new AddressListDto(address.getMember());
            AddressDto addressDto = new AddressDto(address);
            addressListDto.addAddressDto(addressDto);
            addressListDtos.add(addressListDto);
        }
        for (AddressListDto addressListDto : addressListDtos) {
            System.out.println(addressListDto); // AddressListDto의 toString() 메서드가 정의되어 있다면 해당 메서드가 호출되어 값이 출력됩니다.
            // 또는 원하는 속성을 개별적으로 출력할 수도 있습니다.
            // ...
        }


        return new PageImpl<>(addressListDtos, pageable, totalCount);
    }

    public void updateAddress(AddressDto addressDto) {
        AddressEntity addressEntity = addressRepository.findById(addressDto.getAddressId())
                .orElseThrow(EntityNotFoundException::new);
        addressEntity.updateForm(addressDto);
    }

    public void setDefaultAddress(String email, AddressDto addressDto) {
        MemberEntity member = memberRepository.findByMemberEmail(email)
                .orElseThrow(EntityNotFoundException::new);
        List<AddressEntity> addresses = member.getAddresses();
        for (AddressEntity address : addresses) {
            if (address.getId().equals(addressDto.getAddressId())) {

                AddressEntity findAddress = AddressEntity.builder()
                        .address(addressDto.getAddress())
                        .build();
                member.setAddressFromAddressList(findAddress);
                break;
            }
        }
    }


}
