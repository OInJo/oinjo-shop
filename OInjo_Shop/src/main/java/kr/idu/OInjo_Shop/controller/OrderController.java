package kr.idu.OInjo_Shop.controller;

import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import kr.idu.OInjo_Shop.repository.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final MemberRepository memberRepository;
    @GetMapping("/orders/payment")
    public String Orders() {
        return "orderpayment";
    }
    @GetMapping("/orders/form")
    public String addressMain(Model model, HttpSession session) {
        String email = (String) session.getAttribute("loginEmail");
        MemberEntity member = memberRepository.findByMemberEmail(email)
                .orElseThrow(EntityNotFoundException::new);
        MemberDTO memberDto = MemberDTO.toMemberDTO(member);
        model.addAttribute("memberDto", memberDto);
        return "order/orderpayment";
    }
}
