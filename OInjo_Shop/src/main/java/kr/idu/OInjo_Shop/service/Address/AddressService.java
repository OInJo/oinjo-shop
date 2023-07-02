package kr.idu.OInjo_Shop.service.Address;


import kr.idu.OInjo_Shop.repository.Address.AddressRepository;
import kr.idu.OInjo_Shop.repository.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional      //로직을 처리하다가 에러가 발생하였다면, 변경된 데이터를 로직을 수행하기 이전 상태로 콜백 시켜줌.
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final MemberRepository memberRepository;



}
