package kr.idu.OInjo_Shop.dto.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColorDTO {
    private Integer colorId;
    private String colorName;

    public ColorDTO(Integer colorId, String colorName) {
        this.colorId = colorId;
        this.colorName = colorName;
    }
}
