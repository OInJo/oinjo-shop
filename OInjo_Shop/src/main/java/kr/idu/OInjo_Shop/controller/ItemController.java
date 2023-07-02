package kr.idu.OInjo_Shop.controller;

import kr.idu.OInjo_Shop.dto.Item.Relation.BrandDTO;
import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.CategoryDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.ColorDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.SizeDTO;
import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.dto.Page.PageRequestDTO;
import kr.idu.OInjo_Shop.dto.Page.PageResultDTO;
import kr.idu.OInjo_Shop.service.Item.ItemService;
import kr.idu.OInjo_Shop.service.Item.RelationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
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
        return "/admin/itemupload";
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
                              Model model) {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(page)
                .perPage(perPage)
                .perPagination(perPagination)
                .type(type)
                .keyword(keyword)
                .build();

        PageResultDTO<ItemFormDTO, Object[]> itemFormDTOList = itemService.getAllItemList(pageRequestDTO);
        model.addAttribute("itemList", itemFormDTOList);
        return "/test/itemList";
    }

    @GetMapping("/admin/item/{itemId}")
    public String itemDetail(@PathVariable("itemId") Long itemId, Model model) {

        try {
            ItemFormDTO itemFormDTO = itemService.getItemDetail(itemId);
            model.addAttribute("itemFormDTO", itemFormDTO);

        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
            model.addAttribute("itemFormDTO", new ItemFormDTO());
            return "test/itemView";
        }

        return "test/itemView";
    }

    @PostMapping("/admin/item/{itemId}")
    public String itemUpdate(ItemFormDTO itemFormDTO, BindingResult bindingResult,
                             @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, Model model) {

        if(bindingResult.hasErrors()) {
            return "test/itemView";
        }

        if(itemImgFileList.get(0).isEmpty() && itemFormDTO.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입니다.");
            return "test/itemView";
        }

        try {
            itemService.updateItem(itemFormDTO, itemImgFileList);
        } catch (IOException e) {
            model.addAttribute("errorMessage", "상품 수정 중에 오류가 발생했습니다.");
            return "test/itemView";
        }

        return "redirect:/";
    }

    @DeleteMapping("/admin/item/{itemId}/delete")
    public String deleteItemById(@PathVariable("itemId") Long id)
    {
        itemService.deleteItemById(id);
        return "redirect:/member/";
    }


}