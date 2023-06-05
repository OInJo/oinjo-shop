package kr.idu.OInjo_Shop.entity.Item;

import kr.idu.OInjo_Shop.entity.BaseEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.BrandEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.CategoryEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.ColorEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.SizeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity extends BaseEntity {

    @Id
    @Column(name="productId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    // (strategy = GenerationType.AUTO)
    private Long productId; // 상품 코드

    @Column (nullable = false, length = 50)
    private String productName; // 상품명

    @Column (name="price", nullable = false)
    private int productPrice; // 상품 가격

    @Column (nullable = false)
    private int productStock; //재고수량

    @Column
    private String productStatus; // 상품 상태 (품절 or 판매 가능)

    @Column
    private String productDetail; // 상품 설명


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

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemImgEntity> itemImgs = new ArrayList<>();

    public void updateItem(String productName, int productPrice, int productStock, String productStatus,
                           String productDetail, BrandEntity brand, ColorEntity color, SizeEntity size,
                           CategoryEntity category){
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
    public void addItemImg(ItemImgEntity itemImg) {
        itemImgs.add(itemImg);
        itemImg.setItem(this);
    }

    public void removeItemImg(ItemImgEntity itemImg) {
        itemImgs.remove(itemImg);
        itemImg.setItem(null);
    }
}
