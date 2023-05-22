package kr.idu.OInjo_Shop.dto.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandDTO {
    private Integer brandId;
    private String brandName;

    public BrandDTO(Integer brandId, String brandName) {
        this.brandId = brandId;
        this.brandName = brandName;
    }
}
