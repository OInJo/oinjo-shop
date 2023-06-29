package kr.idu.OInjo_Shop.service.Member;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.dto.Page.PageRequestDTO;
import kr.idu.OInjo_Shop.dto.Page.PageResultDTO;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import kr.idu.OInjo_Shop.entity.Member.QMemberEntity;
import kr.idu.OInjo_Shop.repository.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    // final 선언으로 객체 생성, 할당 이후 변경 x


    public void save(MemberDTO memberDTO) {
        // 1. dto -> entity 변환
        // 2. repository의 save 메서드 호출
        String rawPassword = memberDTO.getMemberPassword(); // 사용자가 입력한 비밀번호
        String encodedPassword = passwordEncoder.encode(rawPassword); // 비밀번호 해싱

        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberEntity.setMemberPassword(encodedPassword); // 해싱된 비밀번호로 변경
        memberRepository.save(memberEntity);
        // validateDuplicateMember(memberEntity); 중복 회원 기능 미구현
        // repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
    }

    /* 중복 회원 기능 미구현 private void validateDuplicateMember(MemberEntity member) {
        Optional<MemberEntity> findMember = memberRepository.findByMemberEmail(member.getMemberEmail());
        if(findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다");
        }
    } */

    public MemberDTO login(MemberDTO memberDTO) {
        // 1. 회원이 입력한 이메일로 DB에서 조회
        // 2. DB에서 조회한 비밀번호와 입력한 비밀번호가 일치하는지 판단
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (byMemberEmail.isPresent()) {
            //조회 결과가 존재
            MemberEntity memberEntity = byMemberEmail.get();
            // get 메서드로 optional 객체를 언래핑
            String encodedPassword = memberEntity.getMemberPassword();
            /* matches 사용 안하면 아래와 같이도 가능
            System.out.println(memberEntity.getMemberPassword().equals(encodedPassword));
            */
            if (passwordEncoder.matches(memberDTO.getMemberPassword(), encodedPassword)){
                // 해싱 비밀번호 일치(matches(해싱 전 비밀번호, 인코딩된 비밀번호)
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
    public PageResultDTO<MemberDTO, MemberEntity> getAllList(PageRequestDTO requestDTO) {
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = requestDTO.getPageable(sort);
        BooleanBuilder booleanBuilder = findByCondition(requestDTO);
        Page<MemberEntity> result = memberRepository.findAll(booleanBuilder, pageable);
        Function<MemberEntity, MemberDTO> fn = (entity -> MemberDTO.toMemberDTO(entity));
        PageResultDTO pageResultDTO = new PageResultDTO<>(result, fn, requestDTO.getPerPagination());
        return pageResultDTO;
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

    private BooleanBuilder findByCondition(PageRequestDTO pageRequestDTO) {

        String type = pageRequestDTO.getType();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QMemberEntity qMemberEntity = QMemberEntity.memberEntity;
        // 없을 경우 Gradle clean 후 build

        BooleanExpression expression = qMemberEntity.id.gt(0L);
        booleanBuilder.and(expression);

        if(type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }

        String keyword = pageRequestDTO.getKeyword();

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("e")) { // email로 검색
            conditionBuilder.or(qMemberEntity.memberEmail.contains(keyword));
        }
        if(type.contains("n")) { // name로 검색
            conditionBuilder.or(qMemberEntity.memberName.contains(keyword));
        }
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
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
