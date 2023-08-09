package kr.idu.OInjo_Shop.service.Item.Relation;

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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelationServiceImpl implements RelationService {

    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;

    @Override
    public Long saveBrand(BrandDTO brandDTO){

        // 브랜드 등록
        BrandEntity brand = brandDTO.createBrand();
        brandRepository.save(brand);

        return brand.getBrandId();
    }

    @Override
    public Long saveCategory(CategoryDTO categoryDTO){

        // 브랜드 등록
        CategoryEntity category = categoryDTO.createCategory();
        categoryRepository.save(category);

        return category.getCategoryId();
    }

    @Override
    public Long saveColor(ColorDTO colorDTO){

        // 브랜드 등록
        ColorEntity color = colorDTO.createColor();
        colorRepository.save(color);

        return color.getColorId();
    }

    @Override
    public Long saveSize(SizeDTO sizeDTO){

        // 브랜드 등록
        SizeEntity size = sizeDTO.createSize();
        sizeRepository.save(size);

        return size.getSizeId();
    }

    @Override
    public List<BrandDTO> findAllBrand() {
        List<BrandEntity> brandEntityList = brandRepository.findAll();
        List<BrandDTO> brandDTOList = new ArrayList<>();
        for (BrandEntity brandEntity: brandEntityList) {
            brandDTOList.add(BrandDTO.of(brandEntity));
        }
        return brandDTOList;
    }

    @Override
    public List<CategoryDTO> findAllCategory() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (CategoryEntity categoryEntity: categoryEntityList) {
            categoryDTOList.add(CategoryDTO.of(categoryEntity));
        }
        return categoryDTOList;
    }

    @Override
    public List<ColorDTO> findAllColor() {
        List<ColorEntity> colorEntityList = colorRepository.findAll();
        List<ColorDTO> colorDTOList = new ArrayList<>();
        for (ColorEntity colorEntity: colorEntityList) {
            colorDTOList.add(ColorDTO.of(colorEntity));
        }
        return colorDTOList;
    }

    @Override
    public List<SizeDTO> findAllSize() {
        List<SizeEntity> sizeEntityList = sizeRepository.findAll();
        List<SizeDTO> sizeDTOList = new ArrayList<>();
        for (SizeEntity sizeEntity: sizeEntityList) {
            sizeDTOList.add(SizeDTO.of(sizeEntity));
        }
        return sizeDTOList;
    }

    @Override
    public void deleteBrandById(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteColorById(Long id) {
        colorRepository.deleteById(id);
    }

    @Override
    public void deleteSizeById(Long id) {
        sizeRepository.deleteById(id);
    }
}
