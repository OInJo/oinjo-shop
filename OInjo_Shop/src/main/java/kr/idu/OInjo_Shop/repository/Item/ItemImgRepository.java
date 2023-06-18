package kr.idu.OInjo_Shop.repository.Item;

import kr.idu.OInjo_Shop.entity.Item.ItemImgEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemImgRepository extends JpaRepository<ItemImgEntity, Long> {
}

