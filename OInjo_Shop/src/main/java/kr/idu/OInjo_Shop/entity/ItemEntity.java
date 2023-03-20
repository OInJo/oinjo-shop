package kr.idu.OInjo_Shop.entity;

import kr.idu.OInjo_Shop.dto.ItemFormDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="product")
@Getter
@Setter
@ToString
public class ItemEntity extends BaseEntity {

    @Id
    @Column(name="productId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 상품 코드

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

    @Column
    private String productOpt1;

    @Column
    private String productOpt2;

    @ManyToOne
    @JoinColumn(name ="categoryId")
    private categoryEntity category;

    public void updateItem(ItemFormDTO itemFormDto){
        this.productName = itemFormDto.getProductName();
        this.productPrice = itemFormDto.getProductPrice();
        this.productStock = itemFormDto.getProductStock();
        this.productStatus = itemFormDto.getProductStatus();
        this.productDetail = itemFormDto.getProductDetail();
    }
}
