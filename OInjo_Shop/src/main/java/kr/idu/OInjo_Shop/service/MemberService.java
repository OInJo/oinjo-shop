package kr.idu.OInjo_Shop.service;

import com.sun.source.tree.MemberReferenceTree;
import kr.idu.OInjo_Shop.dto.MemberDTO;
import kr.idu.OInjo_Shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void create(MemberDTO memberDTO) {
    }
}
