package kr.idu.OInjo_Shop.service.Mail;

import kr.idu.OInjo_Shop.entity.Mail.MailEntity;
import kr.idu.OInjo_Shop.repository.Member.MailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final MailRepository mailRepository;

    public MailEntity findByEmail(String email){
        return mailRepository.findByEmail(email);
    }
}
