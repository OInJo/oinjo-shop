package kr.idu.OInjo_Shop.repository.Item;

import kr.idu.OInjo_Shop.entity.Item.ItemEntity;
import kr.idu.OInjo_Shop.entity.Item.ItemImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImgEntity, Long> {

    List<ItemImgEntity> findByItemItemId(Long itemId);

    ItemImgEntity findFirstByItemItemId(Long itemId);

    @Modifying
    @Query("delete from ItemImgEntity i where i.item.itemId = :itemId")
    void deleteByItem(@Param("itemId") Long itemId);


}

