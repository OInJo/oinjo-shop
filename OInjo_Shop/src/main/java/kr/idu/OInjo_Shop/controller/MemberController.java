package kr.idu.OInjo_Shop.controller;

import kr.idu.OInjo_Shop.dto.Page.PageRequestDTO;
import kr.idu.OInjo_Shop.dto.Page.PageResultDTO;
import kr.idu.OInjo_Shop.entity.Mail.MailEntity;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.service.Admin.AdminAuthenticator;
import kr.idu.OInjo_Shop.service.Mail.MailService;
import kr.idu.OInjo_Shop.service.Mail.RegisterMailService;
import kr.idu.OInjo_Shop.service.Member.MemberService;
import kr.idu.OInjo_Shop.service.Member.MemberServiceImpl;
import kr.idu.OInjo_Shop.service.Order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;
    private final RegisterMailService registerMailService;
    private final MailService mailService;
    private final OrderService orderService;
    private final AdminAuthenticator adminAuthenticator;

    // 회원가입
    @GetMapping("/member/save")
    public String saveForm() {
        return "/member/save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "redirect:/";
    }


    @ResponseBody
    @PostMapping("/login/mailAuthentication")
    public ResponseEntity<String> mailConfirm(@RequestParam("email") String email) throws Exception {
        if(!memberService.findByMemberEmail(email)){
            String code = registerMailService.sendSimpleMessage(email);
            System.out.println("인증코드 : " + code);
            return ResponseEntity.ok(code); // 200 OK with the code
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 이메일입니다."); // 409 Conflict
        }
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
        return "/member/login";
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
            return "/member/login";
        }
    }

    @GetMapping(value = {"/member/","/member"})
    public String findAll(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                          @RequestParam(value = "perPage", required = false, defaultValue = "10") int perPage,
                          @RequestParam(value = "perPagination", required = false, defaultValue = "5") int perPagination,
                          @RequestParam(value = "type", required = false, defaultValue = "e") String type,
                          @RequestParam(value = "keyword", required = false, defaultValue = "@") String keyword,
                          Model model,
                          HttpSession session) {

        // 현재 로그인한 사용자의 이메일 주소 가져오기
        String loginEmail = (String) session.getAttribute("loginEmail");

        if (loginEmail != null && adminAuthenticator.isAdmin(loginEmail)) {
            PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                    .page(page)
                    .perPage(perPage)
                    .perPagination(perPagination)
                    .type(type)
                    .keyword(keyword)
                    .build();

            PageResultDTO<MemberDTO, MemberEntity> resultDTO = memberService.getAllList(pageRequestDTO);
            model.addAttribute("result", resultDTO);

            return "/admin/memberlist";
        } else {
            return "redirect:/";
        }
    }


    @GetMapping("/member/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        // qurey 방식은 request param 사용
        // 경로상에 있는 값은 pathvariable 사용
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        // html에서 member.memberEmail 형식으로 사용
        return "admin/memberdetail";
    }
 
    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model) {
        // 정보 수정 -> 세션에 있는 로그인 값으로 전체 정보를 DB로부터 가져와서 model에 담음
        String myEmail = (String) session.getAttribute("loginEmail"); // 캐스팅 - 강제 형변환(Object -> String)
        MemberDTO memberDTO = memberService.findEmail(myEmail);
        model.addAttribute("updateMember", memberDTO);
        return "member/memberupdate";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);
        // 이대로 detail return 시 값 출력 x
        // findById 형식으로 memberDTO를 model에 담아서 return 가능
        // 다른 메서드가 가지고 있는 주소를 요청 - redirect 사용
        // return "redirect:/member/" + memberDTO.getId();
        return "redirect:/"; //회원정보 변경 후 메인페이지로 이동하도록 구현

    }

    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id)
    {
        orderService.deleteMemberById(id);
        memberService.deleteById(id);
        return "redirect:/member/";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session){
        session.invalidate();
        // 세션 초기화
        return "redirect:/";
    }

    @GetMapping("/member/findid")
    public String findEmail(){
        return "/member/findid";
    }

    @PostMapping("/member/findid")
    public String findEmail(@RequestParam(value = "name") String name, @RequestParam(value = "phone") String phone, Model model) {
        MemberDTO memberDTO = memberService.findByMember(name, phone);
        if (memberDTO != null) {
            model.addAttribute("email", memberDTO.getMemberEmail());
        } else {
            model.addAttribute("email", "없습니다.");
        }
        return "/member/findid";
    }

    @GetMapping("/member/findpw")
    public String findPassword(){
        return "/member/findpw";
    }

    @PostMapping("/member/findpw")
    public ResponseEntity<String> temporaryPassword(@RequestParam("email") String email, @RequestParam("code") String code) throws Exception {
        // 이메일과 인증 코드를 받아온다

        // 이메일과 인증 코드를 사용하여 저장된 인증 정보를 조회한다
        MailEntity mailEntity = mailService.findByEmail(email);
        if (mailEntity == null) {
            // 인증 실패 응답을 전송한다
            return ResponseEntity.badRequest().body("존재하지 않는 이메일");
        }

        // 입력된 인증 코드와 저장된 인증 코드를 비교한다
        if (mailEntity.getCode().equals(code)) {
            // 인증 코드 일치. 인증 성공 처리를 수행한다
            String pw = memberService.temporaryPassword(email);
            // 인증 성공 응답을 전송한다
            return ResponseEntity.ok(pw);
        } else {
            // 인증 실패 응답을 전송한다
            return ResponseEntity.badRequest().body("인증번호가 일치하지 않습니다");
        }
    }



}
