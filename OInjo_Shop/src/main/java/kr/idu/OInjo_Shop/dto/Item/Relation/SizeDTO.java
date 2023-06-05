package kr.idu.OInjo_Shop.dto.Item.Relation;

import kr.idu.OInjo_Shop.entity.Item.Relation.SizeEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class SizeDTO {
    private Long sizeId;
    private String sizeName;

    private static ModelMapper modelMapper = new ModelMapper();

    public SizeEntity createSize() {
        return modelMapper.map(this, SizeEntity.class);
    } // entity와 dto의 필드 이름과 타입이 일치하면 매핑 수행

    public static SizeDTO of(SizeEntity Size){
        return modelMapper.map(Size, SizeDTO.class);
    }

}