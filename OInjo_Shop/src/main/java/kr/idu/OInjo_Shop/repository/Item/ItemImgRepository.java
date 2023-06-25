package kr.idu.OInjo_Shop.repository.Item;

import kr.idu.OInjo_Shop.entity.Item.ItemEntity;
import kr.idu.OInjo_Shop.entity.Item.ItemImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImgEntity, Long> {

    List<ItemImgEntity> findByItem(Long itemId);

    @Modifying
    @Query("delete from ItemImgEntity i where i.item = :item")
    void deleteByItem(ItemEntity Item);

}

