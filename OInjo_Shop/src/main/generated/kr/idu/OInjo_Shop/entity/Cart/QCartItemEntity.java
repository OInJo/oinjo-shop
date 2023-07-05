package kr.idu.OInjo_Shop.entity.Cart;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCartItemEntity is a Querydsl query type for CartItemEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCartItemEntity extends EntityPathBase<CartItemEntity> {

    private static final long serialVersionUID = -529306160L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCartItemEntity cartItemEntity = new QCartItemEntity("cartItemEntity");

    public final QCartEntity cart;

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final DatePath<java.time.LocalDate> createDate = createDate("createDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final kr.idu.OInjo_Shop.entity.Item.QItemEntity product;

    public QCartItemEntity(String variable) {
        this(CartItemEntity.class, forVariable(variable), INITS);
    }

    public QCartItemEntity(Path<? extends CartItemEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCartItemEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCartItemEntity(PathMetadata metadata, PathInits inits) {
        this(CartItemEntity.class, metadata, inits);
    }

    public QCartItemEntity(Class<? extends CartItemEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cart = inits.isInitialized("cart") ? new QCartEntity(forProperty("cart"), inits.get("cart")) : null;
        this.product = inits.isInitialized("product") ? new kr.idu.OInjo_Shop.entity.Item.QItemEntity(forProperty("product"), inits.get("product")) : null;
    }

}

