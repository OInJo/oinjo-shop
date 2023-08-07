package kr.idu.OInjo_Shop.controller;

import kr.idu.OInjo_Shop.dto.Item.ItemImgDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.BrandDTO;
import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.CategoryDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.ColorDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.SizeDTO;
import kr.idu.OInjo_Shop.dto.Page.PageRequestDTO;
import kr.idu.OInjo_Shop.dto.Page.PageResultDTO;
import kr.idu.OInjo_Shop.service.Admin.AdminAuthenticator;
import kr.idu.OInjo_Shop.service.Item.ItemImgService;
import kr.idu.OInjo_Shop.service.Item.ItemService;
import kr.idu.OInjo_Shop.service.Item.Relation.RelationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService; // 아이템 및 아이템 이미지
    private final ItemImgService itemImgService;
    private final RelationService relationService; // 카테고리, 브랜드, 컬러, 사이즈
    private final AdminAuthenticator adminAuthenticator; // 어드민 확인 인터페이스

    @GetMapping(value = "/admin/item/new")
    public String itemUploadForm(Model model, HttpSession session) {
        // 현재 로그인한 사용자의 이메일 주소 가져오기
        String loginEmail = (String) session.getAttribute("loginEmail");

        // 로그인한 사용자의 이메일 주소가 특정 이메일과 일치하는지 확인
        if (loginEmail != null && adminAuthenticator.isAdmin(loginEmail)) {
            List<BrandDTO> brandDTOList = relationService.findAllBrand();
            List<CategoryDTO> categoryDTOList = relationService.findAllCategory();
            List<ColorDTO> colorDTOList = relationService.findAllColor();
            List<SizeDTO> sizeDTOList = relationService.findAllSize();
            model.addAttribute("brandList", brandDTOList);
            model.addAttribute("categoryList", categoryDTOList);
            model.addAttribute("colorList", colorDTOList);
            model.addAttribute("sizeList", sizeDTOList);
            model.addAttribute("itemFormDTO", new ItemFormDTO());
            return "/admin/itemupload";
        } else {
            // 특정 이메일이 아닌 경우 접근 제한 처리
            return "redirect:/";
        }
    }




    @PostMapping(value = "/admin/item/new")
    public String itemNew(ItemFormDTO itemFormDTO, BindingResult bindingResult,
                          Model model, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) {

        if (bindingResult.hasErrors()) {
            return "/admin/itemupload";
        }

        if(itemImgFileList.get(0).isEmpty() && itemFormDTO.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력값 입니다.");
            return "/admin/itemupload";
        }

        try {
            itemService.saveItem(itemFormDTO, itemImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            model.addAttribute("errorType", e.getMessage());
            return "/admin/itemupload";
        }

        return "redirect:/";
    }

    @GetMapping(value = {"/admin/item","/admin/item/"})
    public String findAllItem(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                              @RequestParam(value = "perPage", required = false, defaultValue = "10") int perPage,
                              @RequestParam(value = "perPagination", required = false, defaultValue = "5") int perPagination,
                              @RequestParam(value = "type", required = false, defaultValue = "n") String type,
                              @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                              Model model, HttpSession session) {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(page)
                .perPage(perPage)
                .perPagination(perPagination)
                .type(type)
                .keyword(keyword)
                .build();


        // 현재 로그인한 사용자의 이메일 주소 가져오기
        String loginEmail = (String) session.getAttribute("loginEmail");

        if (loginEmail != null && adminAuthenticator.isAdmin(loginEmail)) {
            PageResultDTO<ItemFormDTO, Object[]> itemFormDTOList = itemService.getAllItemList(pageRequestDTO);
            model.addAttribute("itemList", itemFormDTOList);
            List<ItemImgDTO> itemImgDTO = itemImgService.findAllItemImg();
            model.addAttribute("itemImgDTO", itemImgDTO);
            return "/admin/itemList";
        }
        else
            return "redirect:/";
    }

    @GetMapping("/admin/item/{itemId}")
    public String adminItemDetail(@PathVariable("itemId") Long itemId, Model model, HttpSession session) {

        // 현재 로그인한 사용자의 이메일 주소 가져오기
        String loginEmail = (String) session.getAttribute("loginEmail");

        List<ItemImgDTO> itemImgDTO = itemImgService.findItemImgByItemId(itemId);
        model.addAttribute("itemImgDTO", itemImgDTO);

        if (loginEmail != null && adminAuthenticator.isAdmin(loginEmail)) {
            try {
                ItemFormDTO itemFormDTO = itemService.getItemDetail(itemId);
                model.addAttribute("itemFormDTO", itemFormDTO);

            } catch (EntityNotFoundException e) {
                model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
                model.addAttribute("itemFormDTO", new ItemFormDTO());
                return "admin/itemView";
            }

            return "admin/itemView";
        } else {
            return "redirect:/";
        }
    }


    @PostMapping("/admin/item/{itemId}")
    public String itemUpdate(ItemFormDTO itemFormDTO, BindingResult bindingResult,
                             @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, Model model) {

        if(bindingResult.hasErrors()) {
            return "admin/itemView";
        }

        if(itemImgFileList.get(0).isEmpty() && itemFormDTO.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입니다.");
            return "admin/itemView";
        }

        try {
            itemService.updateItem(itemFormDTO, itemImgFileList);
        } catch (IOException e) {
            model.addAttribute("errorMessage", "상품 수정 중에 오류가 발생했습니다.");
            return "admin/itemView";
        }

        return "redirect:/";
    }


        @DeleteMapping("/admin/item/{itemId}/delete")
    public String deleteItemById(@PathVariable("itemId") Long id, HttpSession session)
    {
        itemService.deleteItemById(id);
        return "redirect:/member/";
    }

    @GetMapping("/item/{itemId}")
    public String itemDetail(@PathVariable("itemId") Long itemId, Model model, HttpServletResponse response, HttpSession session) {

            ItemFormDTO itemFormDTO = itemService.getItemDetail(itemId);
            model.addAttribute("itemFormDTO", itemFormDTO);
            List<ItemImgDTO> itemImgDTO = itemImgService.findItemImgByItemId(itemId);
            model.addAttribute("itemImgDTO", itemImgDTO);
            // 현재 로그인한 사용자의 이메일 주소 가져오기
            Long loginId = (Long) session.getAttribute("loginId");
            model.addAttribute("memberId", loginId);

        return "item/detail";
    }
/*
    @PostMapping("/{itemId}/like")
    public String itemLike(@PathVariable("itemId") Long itemId, HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        MemberDTO member = (MemberDTO) session.getAttribute("id");
        if (member == null){
            return "redirect:/member/login";
        }
        int result = itemService.increaseLikesCount(itemId, member.getId());
        if (result == 1) {
            return "redirect:/";
        }
        else
            return "/";
    }*/

}