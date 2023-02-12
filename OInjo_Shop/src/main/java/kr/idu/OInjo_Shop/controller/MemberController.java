package kr.idu.OInjo_Shop.controller;

import kr.idu.OInjo_Shop.dto.MemberDTO;
import kr.idu.OInjo_Shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    // 회원가입
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "index";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        } else {
            // login 실패
            return "login";
        }
    }

    @GetMapping("/member/")
    public String findAll(Model model) {
        // Model 객체 => html로 가져갈 데이터가 있을 경우 사용
        List<MemberDTO> memberDTOList = memberService.findAll();
        // List => DTO 객체가 담겨있음 [여러가지 데이터 가져올 때 List]
        model.addAttribute("memberList", memberDTOList);
        // model 객체로 list 담아감감
        return "list";
    }
}
