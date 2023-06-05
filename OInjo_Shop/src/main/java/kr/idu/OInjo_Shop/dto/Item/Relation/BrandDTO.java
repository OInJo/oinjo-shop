package kr.idu.OInjo_Shop.dto.Item.Relation;

import kr.idu.OInjo_Shop.entity.Item.Relation.BrandEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class BrandDTO {
    private Long brandId;
    private String brandName;

    private static ModelMapper modelMapper = new ModelMapper();
    // DTO와 객체 간의 매핑을 처리 (양이 적어서 수정하는 것도 좋아보임)

    public BrandEntity createBrand() {
        return modelMapper.map(this, BrandEntity.class);
    } // entity와 dto의 필드 이름과 타입이 일치하면 매핑 수행

    public static BrandDTO of(BrandEntity brand){
        return modelMapper.map(brand, BrandDTO.class);
    }

}
