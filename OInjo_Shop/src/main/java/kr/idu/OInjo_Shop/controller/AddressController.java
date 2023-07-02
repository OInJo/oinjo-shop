package kr.idu.OInjo_Shop.controller;

import kr.idu.OInjo_Shop.dto.Address.AddressDto;
import kr.idu.OInjo_Shop.entity.Address.AddressEntity;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import kr.idu.OInjo_Shop.repository.Address.AddressRepository;
import kr.idu.OInjo_Shop.repository.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class AddressController {
    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;
    @GetMapping("/address/new")
    public String getAddress(Model model) {
        AddressDto addressDto = new AddressDto();
        model.addAttribute("addressDto", addressDto);
        return "order/orderpayment";
    }

    @PostMapping("/address/new")
    public String postAddress(AddressDto addressDto, HttpSession session) {
        String email = (String) session.getAttribute("loginEmail"); // 캐스팅 - 강제 형변환(Object -> String)
        System.out.println("==========================="+ email);
        MemberEntity member = memberRepository.findByMemberEmail(email).
                orElseThrow(EntityNotFoundException::new);
        AddressEntity address = AddressEntity.createAddress(member, addressDto);
        addressRepository.save(address);
        member.addAddress(address);         //member에 addresses에도 저장이 됨
        return "redirect:/";
    }

}
