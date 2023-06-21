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
import org.springframework.web.multipart.MultipartFile;

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
    public String brandForm(Model model) {
        List<BrandDTO> brandDTOList = relationService.findAllBrand(); // 브랜드 리스트
        // List => DTO 객체가 담겨있음 [여러가지 데이터 가져올 때 List]
        model.addAttribute("brandList", brandDTOList);
        model.addAttribute("brandDTO", new BrandDTO());
        return "brandupload";
    }

    @GetMapping(value = "/admin/category/new")
    public String categoryForm(Model model) {
        List<CategoryDTO> categoryDTOList = relationService.findAllCategory(); // 카테고리 리스트
        // List => DTO 객체가 담겨있음 [여러가지 데이터 가져올 때 List]
        model.addAttribute("categoryList", categoryDTOList);
        model.addAttribute("categoryDTO", new CategoryDTO());
        return "categoryupload";
    }

    @GetMapping(value = "/admin/color/new")
    public String colorForm(Model model) {
        List<ColorDTO> colorDTOList = relationService.findAllColor(); // 컬러 리스트
        // List => DTO 객체가 담겨있음 [여러가지 데이터 가져올 때 List]
        model.addAttribute("colorList", colorDTOList);
        model.addAttribute("colorDTO", new ColorDTO());
        return "colorupload";
    }

    @GetMapping(value = "/admin/size/new")
    public String sizeForm(Model model) {
        List<SizeDTO> sizeDTOList = relationService.findAllSize(); // 사이즈 리스트
        // List => DTO 객체가 담겨있음 [여러가지 데이터 가져올 때 List]
        model.addAttribute("sizeList", sizeDTOList);
        model.addAttribute("sizeDTO", new SizeDTO());
        return "sizeupload";
    }


    @PostMapping(value = "/admin/item/new")
    public String itemNew(ItemFormDTO itemFormDTO, BindingResult bindingResult,
                          Model model, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) {

        if (bindingResult.hasErrors()) {
            return "upload";
        }

        if(itemImgFileList.get(0).isEmpty() && itemFormDTO.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력값 입니다.");
            return "upload";
        }

        try {
            itemService.saveItem(itemFormDTO, itemImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "upload";
        }

        return "redirect:/";
    }

    @PostMapping(value = "/admin/brand/new")
    public String brandNew(BrandDTO brandDTO, Model model) {

        try {
            if (brandDTO.getBrandName() != null) {
                relationService.saveBrand(brandDTO);
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "브랜드 등록 중 에러가 발생하였습니다.");
            return "uploadbrand";
        }

        return "redirect:/";
    }

    @PostMapping(value = "/admin/category/new")
    public String categoryNew(CategoryDTO categoryDTO, Model model) {

        try {
            if (categoryDTO.getCategoryName() != null) {
                relationService.saveCategory(categoryDTO);
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "카테고리 등록 중 에러가 발생하였습니다.");
            return "uploadcategory";
        }

        return "redirect:/";
    }

    @PostMapping(value = "/admin/color/new")
    public String colorNew(ColorDTO colorDTO, Model model) {

        try {
            if (colorDTO.getColorName() != null) {
                relationService.saveColor(colorDTO);
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "컬러 등록 중 에러가 발생하였습니다.");
            return "uploadcolor";
        }

        return "redirect:/";
    }

    @PostMapping(value = "/admin/size/new")
    public String sizeNew(SizeDTO sizeDTO, Model model){

        try {
            if (sizeDTO.getSizeName() != null) {
                relationService.saveSize(sizeDTO);
            }
        } catch (Exception e){
            model.addAttribute("errorMessage", "사이즈 등록 중 에러가 발생하였습니다.");
            return "uploadsize";
        }

        return "redirect:/";
    }

}