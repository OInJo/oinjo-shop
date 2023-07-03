package kr.idu.OInjo_Shop.repository.Member;

import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>,
        QuerydslPredicateExecutor<MemberEntity> {
    // 이메일로 회원 정보 조회
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
    // 기본적으로 하나를 조회할 때 Repository는 Optional로 넘겨줌

    Optional<MemberEntity> findByMemberNameAndMemberPhone(String name, String phone);
}
