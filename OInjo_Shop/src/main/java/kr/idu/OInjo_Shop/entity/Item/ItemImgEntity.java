package kr.idu.OInjo_Shop.entity.Item;

import kr.idu.OInjo_Shop.entity.BaseEntity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "item_img")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemImgEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_img_id")
    private Long itemImgId;

    private String imgName;

    private String oriName;

    private String imgUrl;

    private String repImgYn;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    public void updateItemImg(String oriName, String imgName, String imgUrl){
        this.oriName = oriName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}
