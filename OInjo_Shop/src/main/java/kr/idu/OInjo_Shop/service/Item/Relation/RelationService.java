package kr.idu.OInjo_Shop.service.Item.Relation;

import kr.idu.OInjo_Shop.dto.Item.Relation.BrandDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.CategoryDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.ColorDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.SizeDTO;

import java.util.List;

public interface RelationService {

    Long saveBrand(BrandDTO brandDTO);
    Long saveCategory(CategoryDTO categoryDTO);
    Long saveColor(ColorDTO colorDTO);
    Long saveSize(SizeDTO sizeDTO);
    List<BrandDTO> findAllBrand();
    List<CategoryDTO> findAllCategory();
    List<ColorDTO> findAllColor();
    List<SizeDTO> findAllSize();
    void deleteBrandById(Long id);
    void deleteCategoryById(Long id);
    void deleteColorById(Long id);
    void deleteSizeById(Long id);
}
