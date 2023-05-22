package kr.idu.OInjo_Shop.dto.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    private Integer categoryId;
    private String categoryName;

    public CategoryDTO(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
