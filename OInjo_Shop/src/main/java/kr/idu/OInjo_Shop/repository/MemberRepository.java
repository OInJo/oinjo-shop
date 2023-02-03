package kr.idu.OInjo_Shop.repository;

import kr.idu.OInjo_Shop.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

}
