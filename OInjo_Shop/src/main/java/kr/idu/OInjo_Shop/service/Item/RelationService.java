package kr.idu.OInjo_Shop.service.Item;

import kr.idu.OInjo_Shop.dto.Item.Relation.BrandDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.CategoryDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.ColorDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.SizeDTO;
import kr.idu.OInjo_Shop.entity.Item.Relation.BrandEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.CategoryEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.ColorEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.SizeEntity;
import kr.idu.OInjo_Shop.repository.Item.Relation.BrandRepository;
import kr.idu.OInjo_Shop.repository.Item.Relation.CategoryRepository;
import kr.idu.OInjo_Shop.repository.Item.Relation.ColorRepository;
import kr.idu.OInjo_Shop.repository.Item.Relation.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelationService {

    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;

    public Long saveBrand(BrandDTO brandDTO) throws Exception{

        // 브랜드 등록
        BrandEntity brand = brandDTO.createBrand();
        brandRepository.save(brand);

        return brand.getBrandId();
    }
    public Long saveCategory(CategoryDTO categoryDTO) throws Exception{

        // 브랜드 등록
        CategoryEntity category = categoryDTO.createCategory();
        categoryRepository.save(category);

        return category.getCategoryId();
    }

    public Long saveColor(ColorDTO colorDTO) throws Exception{

        // 브랜드 등록
        ColorEntity color = colorDTO.createColor();
        colorRepository.save(color);

        return color.getColorId();
    }

    public Long saveSize(SizeDTO sizeDTO) throws Exception{

        // 브랜드 등록
        SizeEntity size = sizeDTO.createSize();
        sizeRepository.save(size);

        return size.getSizeId();
    }
    public List<BrandDTO> findAllBrand() {
        List<BrandEntity> brandEntityList = brandRepository.findAll();
        List<BrandDTO> brandDTOList = new ArrayList<>();
        for (BrandEntity brandEntity: brandEntityList) {
            brandDTOList.add(BrandDTO.of(brandEntity));
        }
        return brandDTOList;
    }
    public List<CategoryDTO> findAllCategory() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (CategoryEntity categoryEntity: categoryEntityList) {
            categoryDTOList.add(CategoryDTO.of(categoryEntity));
        }
        return categoryDTOList;
    }

    public List<ColorDTO> findAllColor() {
        List<ColorEntity> colorEntityList = colorRepository.findAll();
        List<ColorDTO> colorDTOList = new ArrayList<>();
        for (ColorEntity colorEntity: colorEntityList) {
            colorDTOList.add(ColorDTO.of(colorEntity));
        }
        return colorDTOList;
    }

    public List<SizeDTO> findAllSize() {
        List<SizeEntity> sizeEntityList = sizeRepository.findAll();
        List<SizeDTO> sizeDTOList = new ArrayList<>();
        for (SizeEntity sizeEntity: sizeEntityList) {
            sizeDTOList.add(SizeDTO.of(sizeEntity));
        }
        return sizeDTOList;
    }

}
