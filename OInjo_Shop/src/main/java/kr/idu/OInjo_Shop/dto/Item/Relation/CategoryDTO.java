package kr.idu.OInjo_Shop.dto.Item.Relation;

import kr.idu.OInjo_Shop.entity.Item.Relation.CategoryEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;

    private static ModelMapper modelMapper = new ModelMapper();

    public CategoryEntity createCategory() {
        return modelMapper.map(this, CategoryEntity.class);
    } // entity와 dto의 필드 이름과 타입이 일치하면 매핑 수행

    public static CategoryDTO of(CategoryEntity Category){
        return modelMapper.map(Category, CategoryDTO.class);
    }

}
