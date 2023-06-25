package kr.idu.OInjo_Shop.controller;

import kr.idu.OInjo_Shop.dto.Item.Relation.BrandDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.CategoryDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.ColorDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.SizeDTO;
import kr.idu.OInjo_Shop.service.Item.RelationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemRelationController {

    private final RelationService relationService; // 카테고리, 브랜드, 컬러, 사이즈

    @GetMapping(value = "/admin/brand/new")
    public String brandForm(Model model) {
        List<BrandDTO> brandDTOList = relationService.findAllBrand(); // 브랜드 리스트
        // List => DTO 객체가 담겨있음 [여러가지 데이터 가져올 때 List]
        model.addAttribute("brandList", brandDTOList);
        model.addAttribute("brandDTO", new BrandDTO());
        return "brandupload";
    }

    @GetMapping("/admin/brand/delete/{id}")
    public String deleteBrandById(@PathVariable Long id)
    {
        relationService.deleteBrandById(id);
        return "redirect:/brandupload";
    }

    @GetMapping(value = "/admin/category/new")
    public String categoryForm(Model model) {
        List<CategoryDTO> categoryDTOList = relationService.findAllCategory(); // 카테고리 리스트
        // List => DTO 객체가 담겨있음 [여러가지 데이터 가져올 때 List]
        model.addAttribute("categoryList", categoryDTOList);
        model.addAttribute("categoryDTO", new CategoryDTO());
        return "categoryupload";
    }

    @GetMapping("/admin/category/delete/{id}")
    public String deleteCategoryById(@PathVariable Long id)
    {
        relationService.deleteCategoryById(id);
        return "redirect:categoryupload";
    }

    @GetMapping(value = "/admin/color/new")
    public String colorForm(Model model) {
        List<ColorDTO> colorDTOList = relationService.findAllColor(); // 컬러 리스트
        // List => DTO 객체가 담겨있음 [여러가지 데이터 가져올 때 List]
        model.addAttribute("colorList", colorDTOList);
        model.addAttribute("colorDTO", new ColorDTO());
        return "colorupload";
    }

    @GetMapping("/admin/color/delete/{id}")
    public String deleteColorById(@PathVariable Long id)
    {
        relationService.deleteColorById(id);
        return "redirect:/colorupload";
    }

    @GetMapping(value = "/admin/size/new")
    public String sizeForm(Model model) {
        List<SizeDTO> sizeDTOList = relationService.findAllSize(); // 사이즈 리스트
        // List => DTO 객체가 담겨있음 [여러가지 데이터 가져올 때 List]
        model.addAttribute("sizeList", sizeDTOList);
        model.addAttribute("sizeDTO", new SizeDTO());
        return "sizeupload";
    }

    @GetMapping("/admin/size/delete/{id}")
    public String deleteSizeById(@PathVariable Long id)
    {
        relationService.deleteSizeById(id);
        return "redirect:/sizeupload/";
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
