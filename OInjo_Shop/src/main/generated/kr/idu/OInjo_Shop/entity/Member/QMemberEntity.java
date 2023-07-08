package kr.idu.OInjo_Shop.entity.Member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberEntity is a Querydsl query type for MemberEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberEntity extends EntityPathBase<MemberEntity> {

    private static final long serialVersionUID = -310294755L;

    public static final QMemberEntity memberEntity = new QMemberEntity("memberEntity");

    public final ListPath<kr.idu.OInjo_Shop.entity.Address.AddressEntity, kr.idu.OInjo_Shop.entity.Address.QAddressEntity> addresses = this.<kr.idu.OInjo_Shop.entity.Address.AddressEntity, kr.idu.OInjo_Shop.entity.Address.QAddressEntity>createList("addresses", kr.idu.OInjo_Shop.entity.Address.AddressEntity.class, kr.idu.OInjo_Shop.entity.Address.QAddressEntity.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memberAddress = createString("memberAddress");

    public final StringPath memberEmail = createString("memberEmail");

    public final StringPath memberName = createString("memberName");

    public final StringPath memberNickname = createString("memberNickname");

    public final StringPath memberPassword = createString("memberPassword");

    public final StringPath memberPhone = createString("memberPhone");

    public QMemberEntity(String variable) {
        super(MemberEntity.class, forVariable(variable));
    }

    public QMemberEntity(Path<? extends MemberEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberEntity(PathMetadata metadata) {
        super(MemberEntity.class, metadata);
    }

}

