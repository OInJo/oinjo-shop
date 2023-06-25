package kr.idu.OInjo_Shop.Member;

import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import kr.idu.OInjo_Shop.repository.Member.MemberRepository;
import kr.idu.OInjo_Shop.service.Member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberControllerTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void memberListTest() {
        List<MemberDTO> result = memberService.findAll();
        for(MemberDTO m : result)
            System.out.println(m.getMemberEmail());
    }
    @Test
    void saveMemberTest() {
        // Integer 데이터 흐름, Lambda 식 - 함수형 언어의 특징을 활용
        IntStream.rangeClosed(1, 10).forEach(i -> {
            MemberEntity member = MemberEntity.builder()
                    .memberEmail("email" + i + "@oinjo")
                    .memberAddress("서울" + i)
                    .memberName("name" + i)
                    .memberPhone("01050940"+i)
                    .memberNickname("nickName" +i)
                    .memberPassword("pw" +i)
                    .build();
            memberRepository.save(member);
        });
    }


}
