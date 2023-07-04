package kr.idu.OInjo_Shop.controller;

import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.dto.Order.OrderDto;
import kr.idu.OInjo_Shop.entity.Item.ItemEntity;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import kr.idu.OInjo_Shop.repository.Item.ItemRepository;
import kr.idu.OInjo_Shop.repository.Member.MemberRepository;
import kr.idu.OInjo_Shop.service.Item.ItemService;
import kr.idu.OInjo_Shop.service.Order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final ItemService itemService;

    private final OrderService orderService;

    @GetMapping("/orders/form")
    public String orderMain(@RequestParam("itemId") Long itemId, Model model, HttpSession session) {
        String email = (String) session.getAttribute("loginEmail");
        MemberEntity member = memberRepository.findByMemberEmail(email)
                .orElseThrow(EntityNotFoundException::new);
        MemberDTO memberDto = MemberDTO.toMemberDTO(member);
//        ItemEntity item = itemRepository.findById(itemId)     //상품의 아이디를 통해 상품 엔티티를 조회함. 존재하지 않은 경우 예외를 발생시킨다.
//                .orElseThrow(EntityNotFoundException::new);
//        ItemFormDTO itemFormDto = ItemFormDTO.of(item);
        ItemFormDTO itemFormDto = itemService.getItemDetail(itemId);
        model.addAttribute("itemDto", itemFormDto);
        model.addAttribute("memberDto", memberDto);
        return "order/orderpayment";
    }

}
