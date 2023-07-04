package kr.idu.OInjo_Shop.controller;

import kr.idu.OInjo_Shop.dto.Address.AddressDto;
import kr.idu.OInjo_Shop.dto.Address.AddressListDto;
import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.entity.Address.AddressEntity;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import kr.idu.OInjo_Shop.repository.Address.AddressRepository;
import kr.idu.OInjo_Shop.repository.Member.MemberRepository;
import kr.idu.OInjo_Shop.service.Address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.Address;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AddressController {
    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;
    private final AddressService addressService;

    @GetMapping(value = {"/address/list", "/address/list/{page}"})
    public String listAddress(@PathVariable("page") Optional<Integer> page, Model model, HttpSession session) {
        String email = (String) session.getAttribute("loginEmail");
        MemberEntity member = memberRepository.findByMemberEmail(email)
                .orElseThrow(EntityNotFoundException::new);
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 4);  //화면에 나올 주소의 개수는 4개로 설정
        Page<AddressListDto> addressListDtoList =
                addressService.getAddressList(member.getMemberEmail(), pageable);
        model.addAttribute("memberDto", MemberDTO.toMemberDTO(member));
        model.addAttribute("addresses", addressListDtoList);
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("maxPage", 5);

        return "address/addresslist";
    }

    @GetMapping("/address/up-form/{aId}")    //주소의 id로 들어간다.
    public String getAddressDtl(@PathVariable("aId") Long aId, Model model,HttpSession session) {

        AddressEntity address = addressRepository.findById(aId)
                .orElseThrow(EntityNotFoundException::new);
        MemberEntity member = memberRepository.findById((Long) session.getAttribute("loginId"))
                .orElseThrow(EntityNotFoundException::new);

        model.addAttribute("memberDto", MemberDTO.toMemberDTO(member));
        model.addAttribute("addressDto", AddressDto.of(address));
        return "address/up-form";
    }

    @PutMapping("/address/up-form/{addressId}")
    public String putAddressDtl(@ModelAttribute("addressId") AddressDto addressDto) {
        addressService.updateAddress(addressDto);

        return "redirect:/address/list";
    }

    @PutMapping("/address/payment/{id}")
    public @ResponseBody ResponseEntity putDefaultAddress(@PathVariable("id") Long id,
                                                          @RequestBody AddressDto addressDto,
                                                          HttpSession session) {
        try {
            MemberEntity member = memberRepository.findById((Long) session.getAttribute("loginId"))
                    .orElseThrow(EntityNotFoundException::new);
            addressService.setDefaultAddress(member.getMemberEmail(), addressDto);
            System.out.println("==============="+addressDto.getAddressId());
            return ResponseEntity.ok("주소 업데이트가 완료되었습니다아.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("주소 업데이트에 실패했습니다아.");
        }
    }

    @DeleteMapping("/address/delete/{id}")
    public String deleteAddress(@PathVariable("id") Long id, HttpSession session) {
        addressRepository.deleteById(id);
        return "redirect:/address/list";
    }









    @GetMapping("/address/new")
    public String orders(Model model) {

        AddressEntity address = new AddressEntity();
        model.addAttribute("addressDto", new AddressDto());
        return "address/addresspaymentpopup";
    }

    @PostMapping("/address/new")
    public String postAddress(AddressDto addressDto, HttpSession session) {
        String email = (String) session.getAttribute("loginEmail"); // 캐스팅 - 강제 형변환(Object -> String)
        System.out.println("===========================" + email);
        MemberEntity member = memberRepository.findByMemberEmail(email).
                orElseThrow(EntityNotFoundException::new);
        AddressEntity address = AddressEntity.createAddress(member, addressDto);
        addressRepository.save(address);
        member.addAddress(address);         //member에 addresses에도 저장이 됨
        return "redirect:/address/list";
    }
}