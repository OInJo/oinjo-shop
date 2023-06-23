package kr.idu.OInjo_Shop.dto.Item;


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

    public static ItemImgDTO of(ItemImgDTO itemImg) {
        return modelMapper.map(itemImg, ItemImgDTO.class);
    }
}
