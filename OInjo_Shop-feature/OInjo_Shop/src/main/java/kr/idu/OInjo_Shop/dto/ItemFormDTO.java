package kr.idu.OInjo_Shop.dto;

import kr.idu.OInjo_Shop.entity.ItemEntity;
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
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
    private static ModelMapper modelMapper = new ModelMapper();

    public ItemEntity createItem() {
        return modelMapper.map(this, ItemEntity.class);
    }
    public static ItemFormDTO of(ItemEntity item){
        return modelMapper.map(item, ItemFormDTO.class);
    }

}
