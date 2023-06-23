package kr.idu.OInjo_Shop.controller;

import kr.idu.OInjo_Shop.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class HomeController {

    private final HttpSession httpSession;  //RequiredArgsConstructor 어노테이션으로 생성자 자동 생성
    // 기본페이지 요청 메서드
    @GetMapping("/")
    public String index(Model model) {
        //앞서 작성된 UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("username", user.getNickname());
        }

        return "index"; // templates/index.html
    }
    @GetMapping("/email")
    public String email() {
        return "email"; // templates/email.html
    }

}
