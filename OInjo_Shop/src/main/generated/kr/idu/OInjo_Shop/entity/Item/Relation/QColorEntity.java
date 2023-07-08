package kr.idu.OInjo_Shop.entity.Item.Relation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QColorEntity is a Querydsl query type for ColorEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QColorEntity extends EntityPathBase<ColorEntity> {

    private static final long serialVersionUID = 457033787L;

    public static final QColorEntity colorEntity = new QColorEntity("colorEntity");

    public final NumberPath<Long> colorId = createNumber("colorId", Long.class);

    public final StringPath colorName = createString("colorName");

    public QColorEntity(String variable) {
        super(ColorEntity.class, forVariable(variable));
    }

    public QColorEntity(Path<? extends ColorEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QColorEntity(PathMetadata metadata) {
        super(ColorEntity.class, metadata);
    }

}

