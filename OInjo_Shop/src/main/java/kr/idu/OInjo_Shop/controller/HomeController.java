package kr.idu.OInjo_Shop.controller;

import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.dto.Item.ItemImgDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.SizeDTO;
import kr.idu.OInjo_Shop.service.Item.ItemImgService;
import kr.idu.OInjo_Shop.service.Item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    // 기본페이지 요청 메서드
    private final ItemService itemService; // 아이템 및 아이템 이미지
    private final ItemImgService itemImgService; // 아이템 및 아이템 이미지
    @GetMapping("/")
    public String index(Model model) {
        List<ItemFormDTO> itemFormDTOList = itemService.findAllItem();
        List<ItemImgDTO> itemImgDTOList = itemImgService.findAllItemImg();
        return "index"; // templates/index.html
    }
    @GetMapping("/email")
    public String email() {
        return "email"; // templates/email.html
    }

}
