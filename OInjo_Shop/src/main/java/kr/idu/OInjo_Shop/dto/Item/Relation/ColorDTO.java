package kr.idu.OInjo_Shop.dto.Item.Relation;

import kr.idu.OInjo_Shop.entity.Item.Relation.ColorEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ColorDTO {
    private Long colorId;
    private String colorName;

    private static ModelMapper modelMapper = new ModelMapper();

    public ColorEntity createColor() {
        return modelMapper.map(this, ColorEntity.class);
    } // entity와 dto의 필드 이름과 타입이 일치하면 매핑 수행

    public static ColorDTO of(ColorEntity Color){
        return modelMapper.map(Color, ColorDTO.class);
    }

}
