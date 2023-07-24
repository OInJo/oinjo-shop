package kr.idu.OInjo_Shop.entity.Item.Like;

import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.annotation.Target;

@Entity
@Table(name = "item_like")

@Getter
public class ItemLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_like_seq_gen")
    @SequenceGenerator(sequenceName = "item_like_seq", name = "item_like_seq_gen",initialValue = 1, allocationSize = 1)
    // Oracle : GenerationType.SEQUENCE, Mysql/MariaDB : GenerationType.IDENTITY, auto_increment
    private Long itemLikeId; // 유일키

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity member; // 1:1 연관 관계 지정 : 회원 1 : 좋아요 1

    @ManyToOne(fetch = FetchType.LAZY)
    private ItemLikeEntity item; // 1:N 연관 관계 지정 : 아이템 1 : 좋아요 N

}
