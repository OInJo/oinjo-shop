package kr.idu.OInjo_Shop.repository.Member;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;


public interface RegisterMailRepository {
    // 메일 내용 작성
    MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException;

    // 랜덤 인증 코드 전송
    String createKey();

    // 메일 발송
    // sendSimpleMessage 의 매개변수로 들어온 to 는 곧 이메일 주소가 되고,
    // MimeMessage 객체 안에 내가 전송할 메일의 내용을 담는다.
    // 그리고 bean 으로 등록해둔 javaMail 객체를 사용해서 이메일 send!!
    String sendSimpleMessage(String to) throws Exception;

    String sendPassword(String to) throws Exception;
}
