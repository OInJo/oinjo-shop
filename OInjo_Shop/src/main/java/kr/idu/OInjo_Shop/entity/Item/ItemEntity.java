package kr.idu.OInjo_Shop.entity.Item;

import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

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

    public void updateItem(ItemFormDTO itemFormDto){
        this.productName = itemFormDto.getProductName();
        this.productPrice = itemFormDto.getProductPrice();
        this.productStock = itemFormDto.getProductStock();
        this.productStatus = itemFormDto.getProductStatus();
        this.productDetail = itemFormDto.getProductDetail();
        this.brand = itemFormDto.getBrand();
        this.color = itemFormDto.getColor();
        this.size = itemFormDto.getSize();
        this.category = itemFormDto.getCategory();
    }
}
