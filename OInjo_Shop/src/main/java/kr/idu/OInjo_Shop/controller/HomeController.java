package kr.idu.OInjo_Shop.controller;

import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.dto.Item.ItemImgDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.BrandDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.CategoryDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.ColorDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.SizeDTO;
import kr.idu.OInjo_Shop.dto.Member.SessionUser;
import kr.idu.OInjo_Shop.service.Item.ItemImgService;
import kr.idu.OInjo_Shop.service.Item.ItemService;
import kr.idu.OInjo_Shop.service.Item.RelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class HomeController {

    private final HttpSession httpSession;  //RequiredArgsConstructor 어노테이션으로 생성자 자동 생성
    // 기본페이지 요청 메서드
    private final ItemService itemService; // 아이템 및 아이템 이미지
    private final ItemImgService itemImgService; // 아이템 및 아이템 이미지
    private final RelationService relationService; // 브랜드,카테고리,사이즈,컬러

    @GetMapping("/")
    public String index(Model model) {
        List<ItemFormDTO> itemFormDTOList = itemService.findAllItem(); // 아이템 리스트
        List<ItemImgDTO> itemImgDTOList = itemImgService.findAllItemImg(); // 아이템 이미지 리스트
        List<BrandDTO> brandDTOList = relationService.findAllBrand(); // 브랜드 리스트
        List<CategoryDTO> categoryDTOList = relationService.findAllCategory(); // 카테고리 리스트
        List<ColorDTO> colorDTOList = relationService.findAllColor(); // 컬러 리스트
        List<SizeDTO> sizeDTOList = relationService.findAllSize(); // 사이즈 리스트
        model.addAttribute("brandList", brandDTOList); // 브랜드 리스트 받아오기
        model.addAttribute("categoryList", categoryDTOList); // 카테고리 리스트 받아오기
        model.addAttribute("colorList", colorDTOList); // 컬러 리스트 받아오기
        model.addAttribute("sizeList", sizeDTOList); // 사이즈 리스트 받아오기
        model.addAttribute("itemImgDTOList", itemImgDTOList); // 아이템 이미지 리스트 받아오기
        model.addAttribute("itemFormDTOList", itemFormDTOList); // 아이템 리스트 받아오기

        //앞서 작성된 UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("username", user.getNickname());
        }

        return "index"; // templates/index.html
    }
    @GetMapping("/email")
    public String email() {
        return "email"; // templates/email.html
    }

}
