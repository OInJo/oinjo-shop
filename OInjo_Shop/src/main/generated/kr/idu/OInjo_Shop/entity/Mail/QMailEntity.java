package kr.idu.OInjo_Shop.entity.Mail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMailEntity is a Querydsl query type for MailEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMailEntity extends EntityPathBase<MailEntity> {

    private static final long serialVersionUID = 1474571325L;

    public static final QMailEntity mailEntity = new QMailEntity("mailEntity");

    public final StringPath code = createString("code");

    public final DateTimePath<java.time.LocalDateTime> createdTime = createDateTime("createdTime", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QMailEntity(String variable) {
        super(MailEntity.class, forVariable(variable));
    }

    public QMailEntity(Path<? extends MailEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMailEntity(PathMetadata metadata) {
        super(MailEntity.class, metadata);
    }

}

