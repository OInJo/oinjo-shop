package kr.idu.OInjo_Shop.controller;

import kr.idu.OInjo_Shop.dto.Item.Relation.BrandDTO;
import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.CategoryDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.ColorDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.SizeDTO;
import kr.idu.OInjo_Shop.service.Item.ItemService;
import kr.idu.OInjo_Shop.service.Item.RelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService; // 아이템 및 아이템 이미지
    private final RelationService relationService; // 카테고리, 브랜드, 컬러, 사이즈

    @GetMapping(value = "/admin/item/new")
    public String productForm(Model model) {
        List<BrandDTO> brandDTOList = relationService.findAllBrand(); // 브랜드 리스트
        List<CategoryDTO> categoryDTOList = relationService.findAllCategory(); // 카테고리 리스트
        List<ColorDTO> colorDTOList = relationService.findAllColor(); // 컬러 리스트
        List<SizeDTO> sizeDTOList = relationService.findAllSize(); // 사이즈 리스트
        model.addAttribute("brandList", brandDTOList); // 브랜드 리스트 받아오기
        model.addAttribute("categoryList", categoryDTOList); // 카테고리 리스트 받아오기
        model.addAttribute("colorList", colorDTOList); // 컬러 리스트 받아오기
        model.addAttribute("sizeList", sizeDTOList); // 사이즈 리스트 받아오기
        model.addAttribute("itemFormDTO", new ItemFormDTO());
        return "upload";
    }
    @GetMapping(value = "/admin/brand/new")
    public String categoryForm(Model model) {
        List<BrandDTO> brandDTOList = relationService.findAllBrand(); // 브랜드 리스트
        // List => DTO 객체가 담겨있음 [여러가지 데이터 가져올 때 List]
        model.addAttribute("brandList", brandDTOList);
        model.addAttribute("brandDTO", new BrandDTO());
        return "brand";
    }


    @PostMapping(value = "/admin/item/new")
    public String itemNew(ItemFormDTO itemFormDTO, BindingResult bindingResult,
                          Model model){

        if(bindingResult.hasErrors()){
            return "upload";
    }

        try {
            itemService.saveItem(itemFormDTO);
        } catch (Exception e){
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "upload";
        }

        return "redirect:/";
    }

    @PostMapping(value = "/admin/item/classification")
    public String brandNew(BrandDTO brandDTO, CategoryDTO categoryDTO, ColorDTO colorDTO, SizeDTO sizeDTO,Model model){

        try {
            if (brandDTO.getBrandName() != null) {
                relationService.saveBrand(brandDTO);
            }

            if (categoryDTO.getCategoryName() != null) {
                relationService.saveCategory(categoryDTO);
            }

            if (colorDTO.getColorName() != null) {
                relationService.saveColor(colorDTO);
            }

            if (sizeDTO.getSizeName() != null) {
                relationService.saveSize(sizeDTO);
            }
        } catch (Exception e){
            model.addAttribute("errorMessage", "브랜드 등록 중 에러가 발생하였습니다.");
            return "upload";
        }

        return "redirect:/";
    }
}
