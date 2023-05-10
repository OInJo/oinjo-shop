package kr.idu.OInjo_Shop.repository;

import kr.idu.OInjo_Shop.entity.Item.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    }
