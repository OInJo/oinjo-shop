package kr.idu.OInjo_Shop.dto.Item;

import kr.idu.OInjo_Shop.constant.ItemSellStatus;
import kr.idu.OInjo_Shop.entity.Item.*;
import kr.idu.OInjo_Shop.entity.Item.Relation.BrandEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.CategoryEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.ColorEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.SizeEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString
public class ItemFormDTO {

    private Long id;
    private String itemName;
    private Integer itemPrice;
    private String itemDetail;
    private Integer itemStock;
    private Integer itemCategory;

    private ItemSellStatus itemSellStatus;

    private BrandEntity brand;
    private ColorEntity color;
    private SizeEntity size;
    private CategoryEntity category;

    private LocalDateTime regTime;
    private LocalDateTime updateTime;

    private String createdBy;
    private String modifiedBy;


    private List<ItemImgDTO> itemImgDtoList = new ArrayList<>();
    private List<Long> itemImgIds = new ArrayList<>();


    private static ModelMapper modelMapper = new ModelMapper();
    // DTO와 객체 간의 매핑을 처리
    public ItemEntity createItem() {
        return modelMapper.map(this, ItemEntity.class);
    } // entity와 dto의 필드 이름과 타입이 일치하면 매핑 수행
    public static ItemFormDTO of(ItemEntity item){
        return modelMapper.map(item, ItemFormDTO.class);
    }

}
