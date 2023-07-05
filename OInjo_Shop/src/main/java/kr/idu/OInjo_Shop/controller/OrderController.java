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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

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
    @PostMapping("/order")
    public @ResponseBody ResponseEntity order(@RequestBody OrderDto orderDto, BindingResult bindingResult,
                                              HttpSession session) {
        String email = (String)session.getAttribute("loginEmail");
        Long orderId;
        try {
            orderId = orderService.order(orderDto, email);  //화면으로부터 넘어오는 주문 정보와 회원의 이메일 정보를 이용하여 주문 로직을 호출
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);        //결과값으로 생성된 주문 정보와 요청이 성공했다는 HTTP 응답 상태 코드 반환
    }
}
