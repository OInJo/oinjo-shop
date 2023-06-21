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
    @Column(name="productId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId; // 상품 코드

    @Column (nullable = false, length = 50)
    private String productName; // 상품명

    @Column (name="price", nullable = false)
    private int productPrice; // 상품 가격

    @Column (nullable = false)
    private int productStock; //재고수량

    @Enumerated(EnumType.STRING)
    private ItemSellStatus productStatus; // 상품 상태 (품절 or 판매 가능)

    @Column(nullable = false)
    private String productDetail; // 상품 설명


    @OneToOne
    @JoinColumn(name ="brandName")
    private BrandEntity brand;

    @OneToOne
    @JoinColumn(name ="colorName")
    private ColorEntity color;

    @OneToOne
    @JoinColumn(name ="sizeName")
    private SizeEntity size;

    @OneToOne
    @JoinColumn(name ="categoryName")
    private CategoryEntity category;


    public void updateItem(ItemFormDTO itemFormDTO){
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productStatus = productStatus;
        this.productDetail = productDetail;
        this.brand = brand;
        this.color = color;
        this.size = size;
        this.category = category;
    }
}
