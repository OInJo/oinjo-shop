package kr.idu.OInjo_Shop.repository.Address;

import kr.idu.OInjo_Shop.entity.Address.AddressEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    @Query("select a from AddressEntity a " +
            "where a.member.memberEmail =:email"      ////AddressEntity 엔티티에서 memberEmail 속성이 주어진 email 값과 일치하는 레코드를 검색
    )
    List<AddressEntity> findAddresses(@Param("email") String email, Pageable pageable);     //email값이 같은 레코드를 페이징 처리된 형태로 검색하여 반환

    @Query("select count(a) from AddressEntity a " +
            "where a.member.memberEmail =:email"
    )
    Long countAddress(@Param("email") String email);        //memberEmail이 주어진 email 값과 일치하는 레코드의 개수를 반환
}
