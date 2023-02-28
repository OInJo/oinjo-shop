package kr.idu.OInjo_Shop.service;

import kr.idu.OInjo_Shop.dto.MemberDTO;
import kr.idu.OInjo_Shop.entity.MemberEntity;
import kr.idu.OInjo_Shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        // 1. dto -> entity 변환
        // 2. repository의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        System.out.println(memberEntity);
        validateDuplicateMember(memberEntity);
        System.out.println(memberEntity);
        // repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
    }

    private void validateDuplicateMember(MemberEntity member) {
        Optional<MemberEntity> findMember = memberRepository.findByMemberEmail(member.getMemberEmail());
        if(findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다");
        }
    }

    public MemberDTO login(MemberDTO memberDTO) {
        // 1. 회원이 입력한 이메일로 DB에서 조회
        // 2. DB에서 조회한 비밀번호와 입력한 비밀번호가 일치하는지 판단
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (byMemberEmail.isPresent()) {
            //조회 결과가 존재
            MemberEntity memberEntity = byMemberEmail.get();
            // get 메서드로 optional 객체를 언래핑
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                // 비밀번호 일치
                // entity -> dto로 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                // 비밀번호 불일치
                return null;
            }
        } else {
            //조회 결과가 부재
            return null;
        }
    }
    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        // 여기서 findAll()은 repository에서 제공하는 메서드 [List 객체 넘어옴]
        // repository <=> entity로 주고 받기
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity: memberEntityList) {
            // for each문 사용 memberDTOList => DTO 객체를 받음
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
            //entity list 객체를 dto로 변환 후 controller로 넘김
            // 아래와 같이 두줄로 가능
            //MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            //memberDTOList.add(memberDTO);
        }
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
            /*
            MemberEntity memberEntity = optionalMemberEntity.get();
            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            return memberDTO;
            */
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }
    
    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEmail = memberRepository.findByMemberEmail(myEmail);
        if (optionalMemberEmail.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEmail.get());
        } else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
        // save 메서드는 ID가 없으면 insert 쿼리 수행, DB에 있을 경우 update 쿼리 수행
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}
