package kr.idu.OInjo_Shop.entity.Item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemEntity is a Querydsl query type for ItemEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemEntity extends EntityPathBase<ItemEntity> {

    private static final long serialVersionUID = -958265923L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemEntity itemEntity = new QItemEntity("itemEntity");

    public final kr.idu.OInjo_Shop.entity.QBaseEntity _super = new kr.idu.OInjo_Shop.entity.QBaseEntity(this);

    public final kr.idu.OInjo_Shop.entity.Item.Relation.QBrandEntity brand;

    public final kr.idu.OInjo_Shop.entity.Item.Relation.QCategoryEntity category;

    public final kr.idu.OInjo_Shop.entity.Item.Relation.QColorEntity color;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath itemDetail = createString("itemDetail");

    public final NumberPath<Long> itemId = createNumber("itemId", Long.class);

    public final StringPath itemName = createString("itemName");

    public final NumberPath<Integer> itemPrice = createNumber("itemPrice", Integer.class);

    public final EnumPath<kr.idu.OInjo_Shop.constant.ItemSellStatus> itemSellStatus = createEnum("itemSellStatus", kr.idu.OInjo_Shop.constant.ItemSellStatus.class);

    public final NumberPath<Integer> itemStock = createNumber("itemStock", Integer.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public final kr.idu.OInjo_Shop.entity.Item.Relation.QSizeEntity size;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QItemEntity(String variable) {
        this(ItemEntity.class, forVariable(variable), INITS);
    }

    public QItemEntity(Path<? extends ItemEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemEntity(PathMetadata metadata, PathInits inits) {
        this(ItemEntity.class, metadata, inits);
    }

    public QItemEntity(Class<? extends ItemEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.brand = inits.isInitialized("brand") ? new kr.idu.OInjo_Shop.entity.Item.Relation.QBrandEntity(forProperty("brand")) : null;
        this.category = inits.isInitialized("category") ? new kr.idu.OInjo_Shop.entity.Item.Relation.QCategoryEntity(forProperty("category")) : null;
        this.color = inits.isInitialized("color") ? new kr.idu.OInjo_Shop.entity.Item.Relation.QColorEntity(forProperty("color")) : null;
        this.size = inits.isInitialized("size") ? new kr.idu.OInjo_Shop.entity.Item.Relation.QSizeEntity(forProperty("size")) : null;
    }

}

