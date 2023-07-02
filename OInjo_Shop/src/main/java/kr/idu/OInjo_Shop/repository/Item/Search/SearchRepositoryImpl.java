package kr.idu.OInjo_Shop.repository.Item.Search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import kr.idu.OInjo_Shop.entity.Item.ItemEntity;
import kr.idu.OInjo_Shop.entity.Item.QItemEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.QBrandEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.QCategoryEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
@Qualifier("SearchRepositoryImpl")
@Log4j2
public class SearchRepositoryImpl extends QuerydslRepositorySupport implements SearchRepository{
    
    public SearchRepositoryImpl() {
        super(ItemEntity.class);
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        QItemEntity itemEntity = QItemEntity.itemEntity;
        QBrandEntity brandEntity = QBrandEntity.brandEntity;
        QCategoryEntity categoryEntity = QCategoryEntity.categoryEntity;

        JPQLQuery<ItemEntity> jpqlQeury = from(itemEntity);
        jpqlQeury.leftJoin(brandEntity).on(itemEntity.brand.eq(brandEntity));
        jpqlQeury.leftJoin(categoryEntity).on(itemEntity.category.eq(categoryEntity));
        // select b, w from ItemEntity b left join b.brand w on b.category = w;

        // select b, w, count(r) from itemEntity b left join b.writer w left join categoryEntity r on r.item = b;
        JPQLQuery<Tuple> tuple = jpqlQeury.select(itemEntity, brandEntity, categoryEntity);
        //JPQLQuery<Tuple> tuple = jpqlQeury.select(itemEntity, brandEntity);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression= itemEntity.itemId.gt(0L); // sequence number > 0L 모두 만족하므로 모두임

        booleanBuilder.and(expression);

        if(type != null) {
            String[] typeArr = type.split("");
            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for(String t : typeArr) {
                switch (t) {
                    case "n":
                        conditionBuilder.or(itemEntity.itemName.contains(keyword)); // 아이템 이름
                        break;
                    case "b":
                        conditionBuilder.or(brandEntity.brandName.contains(keyword)); // 브랜드 이름
                        break;
                    case "c":
                        conditionBuilder.or(categoryEntity.categoryName.contains(keyword)); // 카테고리 이름
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }
        tuple.where(booleanBuilder);

// order by
        Sort sort = pageable.getSort();
// tuple.orderBy(item.itemId.desc());
        sort.stream().forEach(order -> {
            Order direction = order.isAscending()? Order.ASC: Order.DESC;
            String prop = order.getProperty();
            PathBuilder orderByExpression = new PathBuilder(ItemEntity.class, "itemEntity");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        tuple.groupBy(itemEntity, brandEntity, categoryEntity); // Oracle -> MariaDB, Mysql 보다 sql문법 엄격함

// page 처리
        tuple.offset(pageable.getOffset()); // 시작 레코드 vs 현재 페이지를 사용하지는 않음
        tuple.limit(pageable.getPageSize()); // 레코드 수

        List<Tuple> result = tuple.fetch(); // 데이터 소스로 부터 정보를 가져옴

        long count = tuple.fetchCount(); // 갯수를 확인
        return new PageImpl<Object[]>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);

    }
}
