package kr.idu.OInjo_Shop.controller;

import kr.idu.OInjo_Shop.dto.Item.BrandDTO;
import kr.idu.OInjo_Shop.dto.Item.CategoryDTO;
import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.service.Item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    List<BrandDTO> brandDTOList = itemService.findAllBrand(); // 브랜드 리스트

    @GetMapping(value = "/admin/item/new")
    public String productForm(Model model) {
        model.addAttribute("brandList", brandDTOList); // 브랜드 리스트 받아오기
        model.addAttribute("itemFormDTO", new ItemFormDTO());
        return "upload";
    }
    @GetMapping(value = "/admin/brand/new")
    public String categoryForm(Model model) {
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

    @PostMapping(value = "/admin/brand/new")
    public String brandNew(BrandDTO brandDTO, Model model){

        try {
            itemService.saveBrand(brandDTO);
        } catch (Exception e){
            model.addAttribute("errorMessage", "브랜드 등록 중 에러가 발생하였습니다.");
            return "upload";
        }

        return "redirect:/";
    }
}
