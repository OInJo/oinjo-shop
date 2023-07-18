package kr.idu.OInjo_Shop.config;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class LoginUserAuditorAware implements AuditorAware<String> {
    private final HttpSession session;


    @Override
    public Optional<String> getCurrentAuditor() {
        String email = (String) session.getAttribute("loginEmail");
        if(email == null)
            return null;
        return Optional.ofNullable(email);
    }

}
