package kr.idu.OInjo_Shop.dto.Item.Relation;

import kr.idu.OInjo_Shop.entity.Item.Relation.BrandEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.CategoryEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.ColorEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.SizeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class AllRelationDTO {

    private List<BrandDTO> brands;
    private List<CategoryDTO> categories;
    private List<ColorDTO> colors;
    private List<SizeDTO> sizes;

    public static AllRelationDTO of(List<BrandEntity> brands, List<CategoryEntity> categories,
                                    List<ColorEntity> colors, List<SizeEntity> sizes) {
        List<BrandDTO> brandDTOList = brands.stream().map(BrandDTO::of).collect(Collectors.toList());
        List<CategoryDTO> categoryDTOList = categories.stream().map(CategoryDTO::of).collect(Collectors.toList());
        List<ColorDTO> colorDTOList = colors.stream().map(ColorDTO::of).collect(Collectors.toList());
        List<SizeDTO> sizeDTOList = sizes.stream().map(SizeDTO::of).collect(Collectors.toList());

        return new AllRelationDTO(brandDTOList, categoryDTOList, colorDTOList, sizeDTOList);
    }
}
