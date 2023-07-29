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

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemRelationController {

    private final RelationService relationService; // 카테고리, 브랜드, 컬러, 사이즈

    @GetMapping(value = "/admin/brand/new")
    public String brandForm(Model model, HttpSession session) {
        // 특정 이메일을 확인하고자 하는 이메일 주소
        String allowedEmail = "Admin@naver.com";

        // 현재 로그인한 사용자의 이메일 주소 가져오기
        String loginEmail = (String) session.getAttribute("loginEmail");

        if (loginEmail != null && loginEmail.equals(allowedEmail)) {
            List<BrandDTO> brandDTOList = relationService.findAllBrand();
            model.addAttribute("brandList", brandDTOList);
            model.addAttribute("brandDTO", new BrandDTO());
            return "admin/brandupload";
        } else {
            return "redirect:/";
        }
    }


    @GetMapping("/admin/brand/delete/{id}")
    public String deleteBrandById(@PathVariable Long id)
    {
        relationService.deleteBrandById(id);
        // return "redirect:/brandupload";
        return "redirect:/admin/brand/new";
    }

    @GetMapping(value = "/admin/category/new")
    public String categoryForm(Model model, HttpSession session) {
        // 특정 이메일을 확인하고자 하는 이메일 주소
        String allowedEmail = "Admin@naver.com";

        // 현재 로그인한 사용자의 이메일 주소 가져오기
        String loginEmail = (String) session.getAttribute("loginEmail");

        if (loginEmail != null && loginEmail.equals(allowedEmail)) {
            List<CategoryDTO> categoryDTOList = relationService.findAllCategory();
            model.addAttribute("categoryList", categoryDTOList);
            model.addAttribute("categoryDTO", new CategoryDTO());
            return "admin/categoryupload";
        } else {
            return "redirect:/";
        }
    }


    @GetMapping("/admin/category/delete/{id}")
    public String deleteCategoryById(@PathVariable Long id)
    {
        relationService.deleteCategoryById(id);
        // return "redirect:/admin/categoryupload";
        return "redirect:/admin/category/new";
    }

    @GetMapping(value = "/admin/color/new")
    public String colorForm(Model model, HttpSession session) {
        // 특정 이메일을 확인하고자 하는 이메일 주소
        String allowedEmail = "Admin@naver.com";

        // 현재 로그인한 사용자의 이메일 주소 가져오기
        String loginEmail = (String) session.getAttribute("loginEmail");

        if (loginEmail != null && loginEmail.equals(allowedEmail)) {
            List<ColorDTO> colorDTOList = relationService.findAllColor();
            model.addAttribute("colorList", colorDTOList);
            model.addAttribute("colorDTO", new ColorDTO());
            return "admin/colorupload";
        } else {
            return "redirect:/";
        }
    }


    @GetMapping("/admin/color/delete/{id}")
    public String deleteColorById(@PathVariable Long id)
    {
        relationService.deleteColorById(id);
        // return "redirect:/admin/colorupload";
        return "redirect:/admin/color/new";
    }

    @GetMapping(value = "/admin/size/new")
    public String sizeForm(Model model, HttpSession session) {
        // 특정 이메일을 확인하고자 하는 이메일 주소
        String allowedEmail = "Admin@naver.com";

        // 현재 로그인한 사용자의 이메일 주소 가져오기
        String loginEmail = (String) session.getAttribute("loginEmail");

        if (loginEmail != null && loginEmail.equals(allowedEmail)) {
            List<SizeDTO> sizeDTOList = relationService.findAllSize();
            model.addAttribute("sizeList", sizeDTOList);
            model.addAttribute("sizeDTO", new SizeDTO());
            return "admin/sizeupload";
        } else {
            return "redirect:/";
        }
    }


    @GetMapping("/admin/size/delete/{id}")
    public String deleteSizeById(@PathVariable Long id)
    {
        relationService.deleteSizeById(id);
        // return "redirect:/sizeupload/";
        return "redirect:/admin/size/new";
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
