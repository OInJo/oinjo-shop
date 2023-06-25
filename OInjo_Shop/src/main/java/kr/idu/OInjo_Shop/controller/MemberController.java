package kr.idu.OInjo_Shop.controller;

import kr.idu.OInjo_Shop.entity.Mail.MailEntity;
import kr.idu.OInjo_Shop.repository.Member.MailServiceInter;
import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.service.Mail.MailService;
import kr.idu.OInjo_Shop.service.Member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    private final MailService mailService;

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
    @PostMapping("/login/mailAuthentication")
    public String mailConfirm(@RequestParam("email") String email) throws Exception {
        String code = EmailServiceImpl.sendSimpleMessage(email);
        System.out.println("인증코드 : " + code);
        return code;
    }

    @ResponseBody
    @PostMapping("/login/mailConfirm")
    public ResponseEntity<String> confirmMail(@RequestParam("email") String email, @RequestParam("code") String code) {
        // 이메일과 인증 코드를 받아온다

        // 이메일과 인증 코드를 사용하여 저장된 인증 정보를 조회한다
        MailEntity mailEntity = mailService.findByEmail(email);
        if (mailEntity == null) {
            return ResponseEntity.badRequest().body("이메일 주소가 유효하지 않습니다.");
        }

        // 입력된 인증 코드와 저장된 인증 코드를 비교한다
        if (mailEntity.getCode().equals(code)) {
            // 인증 코드 일치. 인증 성공 처리를 수행한다

            // TODO: 인증 성공 처리 로직 추가

            // 인증 성공 응답을 전송한다
            return ResponseEntity.ok("이메일 인증이 완료되었습니다.");
        } else {
            // 인증 코드 불일치. 인증 실패 처리를 수행한다

            // TODO: 인증 실패 처리 로직 추가

            // 인증 실패 응답을 전송한다
            return ResponseEntity.badRequest().body("인증 코드가 일치하지 않습니다.");
        }
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
            session.setAttribute("loginId", loginResult.getId());
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
