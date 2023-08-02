package kr.idu.OInjo_Shop.service.Admin;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AdminAuthentication implements AdminAuthenticator{

    private final List<String> allowedEmails = Arrays.asList("Admin@naver.com", "admin@example.com");

    @Override
    public boolean isAdmin(String email) {
        return allowedEmails.contains(email);
    }
}
