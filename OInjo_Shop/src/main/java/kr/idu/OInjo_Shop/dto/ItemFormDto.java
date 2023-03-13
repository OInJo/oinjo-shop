package kr.idu.OInjo_Shop.dto;

import kr.idu.OInjo_Shop.entity.ItemEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ItemFormDto {

    private static ModelMapper modelMapper = new ModelMapper();
    public ItemEntity createItem() {
        return modelMapper.map(this, ItemEntity.class);
    }
}
