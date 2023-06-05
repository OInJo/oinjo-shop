package kr.idu.OInjo_Shop.repository.Cart;

import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.entity.Cart.CartEntity;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    CartEntity findByMember(MemberDTO userId);
}
