package kr.idu.OInjo_Shop.repository;

import kr.idu.OInjo_Shop.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    }
