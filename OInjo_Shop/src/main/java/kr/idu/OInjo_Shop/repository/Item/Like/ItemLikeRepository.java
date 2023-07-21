package kr.idu.OInjo_Shop.repository.Item.Like;

import kr.idu.OInjo_Shop.entity.Item.ItemEntity;
import kr.idu.OInjo_Shop.entity.Item.Like.ItemLikeEntity;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemLikeRepository extends JpaRepository<ItemLikeEntity, Long> {
    //ItemLikeEntity findBySeqAndBno(MemberEntity memberEntity, ItemEntity itemEntity);
}
