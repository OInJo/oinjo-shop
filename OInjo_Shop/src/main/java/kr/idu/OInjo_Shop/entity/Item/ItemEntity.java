package kr.idu.OInjo_Shop.entity.Item;

import kr.idu.OInjo_Shop.constant.ItemSellStatus;
import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.entity.BaseEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.BrandEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.CategoryEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.ColorEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.SizeEntity;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="item")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity extends BaseEntity {

    @Id
    @Column(name="itemId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId; // 상품 코드

    @Column (nullable = false, length = 50)
    private String itemName; // 상품명

    @Column (name="price", nullable = false)
    private int itemPrice; // 상품 가격

    @Column (nullable = false)
    private int itemStock; //재고수량

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; // 상품 상태 (품절 or 판매 가능)

    @Column(nullable = false)
    private String itemDetail; // 상품 설명


    @OneToOne
    @JoinColumn(name ="brandId")
    private BrandEntity brand;

    @OneToOne
    @JoinColumn(name ="colorId")
    private ColorEntity color;

    @OneToOne
    @JoinColumn(name ="sizeId")
    private SizeEntity size;

    @OneToOne
    @JoinColumn(name ="categoryId")
    private CategoryEntity category;


    public void updateItem(ItemFormDTO itemFormDTO) {
        this.itemName= itemFormDTO.getItemName();
        this.itemPrice = itemFormDTO.getItemPrice();
        this.itemStock = itemFormDTO.getItemStock();
        this.itemSellStatus = itemFormDTO.getItemSellStatus();
        this.itemDetail = itemFormDTO.getItemDetail();
        this.brand = itemFormDTO.getBrand();
        this.color = itemFormDTO.getColor();
        this.size = itemFormDTO.getSize();
        this.category = itemFormDTO.getCategory();
    }

}
