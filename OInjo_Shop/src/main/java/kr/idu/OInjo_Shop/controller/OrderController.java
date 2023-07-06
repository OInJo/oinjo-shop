package kr.idu.OInjo_Shop.controller;

import com.querydsl.core.types.Order;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String orderMain(Model model, HttpSession session) {
        String email = (String) session.getAttribute("loginEmail");
        OrderDto orderDto = (OrderDto) session.getAttribute("orderDto");
        MemberEntity member = memberRepository.findByMemberEmail(email)
                .orElseThrow(EntityNotFoundException::new);
        MemberDTO memberDto = MemberDTO.toMemberDTO(member);
        ItemFormDTO itemFormDto = itemService.getItemDetail(orderDto.getItemId());
        model.addAttribute("orderDto", orderDto);
        model.addAttribute("itemDto", itemFormDto);
        model.addAttribute("memberDto", memberDto);

        return "order/orderpayment";
    }


    @PostMapping("/orders/form")        // /orders/form으로 값을 넘겨주는 역할
    public @ResponseBody ResponseEntity orderSend(@RequestBody OrderDto orderDto, HttpSession session) {
        try {
            session.setAttribute("orderDto", orderDto);
            return ResponseEntity.ok("완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("오류입니다.");
        }
    }

    @PostMapping("/orders/pay")
    public @ResponseBody ResponseEntity orderPay(@RequestBody OrderDto orderDto, HttpSession session) {
        try {
            String email = (String) session.getAttribute("loginEmail");
            orderService.order(orderDto, email);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("완료");
    }

    @GetMapping("/orders/completion")
    public String paymentCompleted(Model model, HttpSession session) {
        String email = (String) session.getAttribute("loginEmail");
        MemberEntity member = memberRepository.findByMemberEmail(email)
                .orElseThrow(EntityNotFoundException::new);

        return "order/paymentcompleted";
    }
}