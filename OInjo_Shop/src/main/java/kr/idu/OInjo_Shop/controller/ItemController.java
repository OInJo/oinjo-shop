package kr.idu.OInjo_Shop.controller;

import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "/admin/item/new")
    public String productForm(Model model) {
        model.addAttribute("itemFormDTO", new ItemFormDTO());
        return "upload";
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
}
