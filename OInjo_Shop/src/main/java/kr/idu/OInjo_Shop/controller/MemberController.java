package kr.idu.OInjo_Shop.controller;

import kr.idu.OInjo_Shop.dto.MemberDTO;
import kr.idu.OInjo_Shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    // 회원가입
    @GetMapping("/member/create")
    public String createForm() {
        return "create";
    }
    @PostMapping("/member/create")
    public String create(@ModelAttribute MemberDTO memberDTO) { // 객체 다받아옴
        memberService.create(memberDTO);
        return "index";
    }
}
