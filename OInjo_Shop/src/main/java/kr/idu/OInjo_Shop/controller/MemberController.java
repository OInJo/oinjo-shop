package kr.idu.OInjo_Shop.controller;

import kr.idu.OInjo_Shop.controller.Mail.MailServiceInter;
import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
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
    private final MailServiceInter EmailServiceImpl;

    // 회원가입
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "redirect:/";
    }


    @ResponseBody
    @PostMapping("login/mailConfirm")
    public String mailConfirm(@RequestParam("email") String email) throws Exception {
        String code = EmailServiceImpl.sendSimpleMessage(email);
        System.out.println("인증코드 : " + code);
        return code;
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
            System.out.print(session);
            return "redirect:/";

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
        return "memberlist";
    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
        // qurey 방식은 request param 사용
        // 경로상에 있는 값은 pathvariable 사용
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        // html에서 member.memberEmail 형식으로 사용
        return "memberdetail";
    }
 
    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model) {
        // 정보 수정 -> 세션에 있는 로그인 값으로 전체 정보를 DB로부터 가져와서 model에 담음
        String myEmail = (String) session.getAttribute("loginEmail"); // 캐스팅 - 강제 형변환(Object -> String)
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDTO);
        return "memberupdate";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);
        // 이대로 detail return 시 값 출력 x
        // findById 형식으로 memberDTO를 model에 담아서 return 가능
        // 다른 메서드가 가지고 있는 주소를 요청 - redirect 사용
        return "redirect:/member/" + memberDTO.getId();

    }

    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id)
    {
        memberService.deleteById(id);
        return "redirect:/member/";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session){
        session.invalidate();
        // 세션 초기화
        return "redirect:/";
    }

}
