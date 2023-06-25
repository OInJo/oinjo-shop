package kr.idu.OInjo_Shop.repository.Item;

import kr.idu.OInjo_Shop.entity.Item.ItemImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImgEntity, Long> {

    List<ItemImgEntity> findByItem(Long itemId);
}

