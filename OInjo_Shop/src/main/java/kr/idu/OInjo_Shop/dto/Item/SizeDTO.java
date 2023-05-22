package kr.idu.OInjo_Shop.dto.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SizeDTO {
    private Integer sizeId;
    private String sizeName;

    public SizeDTO(Integer sizeId, String sizeName) {
        this.sizeId = sizeId;
        this.sizeName = sizeName;
    }
}