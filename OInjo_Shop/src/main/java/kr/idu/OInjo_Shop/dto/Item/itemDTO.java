package kr.idu.OInjo_Shop.dto.Item;

import kr.idu.OInjo_Shop.entity.Item.Relation.BrandEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.CategoryEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.ColorEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.SizeEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class itemDTO {

    private Long id;			// 상품 코드

    private String itemName;	// 상품 이름

    private int price;			// 상품 가격

    private String itemSellStatus;

    private String itemDetail;	// 상품 상세 설명

    private LocalDateTime regTime;		// 등록 시간

    private LocalDateTime updateTime;	// 수정 시간

    private BrandEntity brand;
    private ColorEntity color;
    private SizeEntity size;
    private CategoryEntity category;

}
