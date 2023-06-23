package kr.idu.OInjo_Shop.dto.Item;


import kr.idu.OInjo_Shop.entity.Item.ItemImgEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ItemImgDTO {
    private Long itemImgId;

    private String imgName;

    private String oriName;

    private String imgUrl;

    private String repImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgDTO of(ItemImgEntity itemImg) {
        return modelMapper.map(itemImg, ItemImgDTO.class);
    }
}
