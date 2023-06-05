package kr.idu.OInjo_Shop.repository.Item.Relation;

import kr.idu.OInjo_Shop.entity.Item.Relation.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
