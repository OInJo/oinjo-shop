package kr.idu.OInjo_Shop.dto.Item;

import kr.idu.OInjo_Shop.entity.Item.*;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter @Setter
public class ItemFormDTO {

    private Long id;
    private String productName;
    private Integer productPrice;
    private String productStatus;
    private String productDetail;
    private Integer productStock;
    private Integer productCategory;
    private BrandEntity brand;
    private ColorEntity color;
    private SizeEntity size;
    private CategoryEntity category;

    private LocalDateTime regTime;
    private LocalDateTime updateTime;

    private String createdBy;
    private String modifiedBy;

    private static ModelMapper modelMapper = new ModelMapper();
    // DTO와 객체 간의 매핑을 처리
    public ItemEntity createItem() {
        return modelMapper.map(this, ItemEntity.class);
    } // entity와 dto의 필드 이름과 타입이 일치하면 매핑 수행
    public static ItemFormDTO of(ItemEntity item){
        return modelMapper.map(item, ItemFormDTO.class);
    }

}
