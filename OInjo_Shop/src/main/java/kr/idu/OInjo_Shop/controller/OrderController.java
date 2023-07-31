package kr.idu.OInjo_Shop.controller;

import com.mysema.commons.lang.Pair;
import kr.idu.OInjo_Shop.dto.Address.AddressDto;
import kr.idu.OInjo_Shop.dto.Address.AddressListDto;
import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.dto.Order.OrderAndItemFormDTO;
import kr.idu.OInjo_Shop.dto.Order.OrderDto;
import kr.idu.OInjo_Shop.dto.Order.OrderHistDto;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import kr.idu.OInjo_Shop.entity.Order.OrderEntity;
import kr.idu.OInjo_Shop.repository.Item.ItemRepository;
import kr.idu.OInjo_Shop.repository.Member.MemberRepository;
import kr.idu.OInjo_Shop.repository.Order.OrderRepository;
import kr.idu.OInjo_Shop.service.Address.AddressService;
import kr.idu.OInjo_Shop.service.Item.ItemService;
import kr.idu.OInjo_Shop.service.Order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final AddressService addressService;

//    @GetMapping("/orders/form")
//    public String orderMain(Model model, HttpSession session) {
//        try {
//            String email = (String) session.getAttribute("loginEmail");
//            OrderDto orderDto = (OrderDto) session.getAttribute("orderDto");
//            if (email == null || orderDto == null) {
//                throw new NullPointerException("Session value is empty.");
//            }
//            MemberEntity member = memberRepository.findByMemberEmail(email)
//                    .orElseThrow(EntityNotFoundException::new);
//            MemberDTO memberDto = MemberDTO.toMemberDTO(member);
//            ItemFormDTO itemFormDto = itemService.getItemDetail(orderDto.getItemId());
//            System.out.println(itemFormDto.getRegTime());
//            model.addAttribute("orderDto", orderDto);
//            model.addAttribute("itemDto", itemFormDto);
//            model.addAttribute("memberDto", memberDto);
//
//            return "order/orderpayment";
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            return "member/login";
//        }
//    }
    @GetMapping("/orders/form")
    public String orderMain(Model model, HttpSession session) {
        try {
            String email = (String) session.getAttribute("loginEmail");
            List<OrderDto> orderDtoList = (List<OrderDto>) session.getAttribute("orderDto");
            if (email == null) {
                throw new IllegalStateException("사용자가 로그인하지 않았습니다.");
            }
            if (orderDtoList == null || orderDtoList.isEmpty()) {
                throw new NullPointerException("Session value is empty.");
            }
            MemberEntity member = memberRepository.findByMemberEmail(email)
                    .orElseThrow(EntityNotFoundException::new);
            MemberDTO memberDto = MemberDTO.toMemberDTO(member);
            List<ItemFormDTO> itemFormDtoList = new ArrayList<>();
            for (OrderDto orderDto : orderDtoList) {
                System.out.println(orderDto.getItemId());
                ItemFormDTO itemFormDto = itemService.getItemDetail(orderDto.getItemId());
                System.out.println("itemFormDto값 확인= " + itemFormDto);
                itemFormDtoList.add(itemFormDto);
            }
            //OrderDto와 ItemFormDto값 합치기
            List<OrderAndItemFormDTO> orderAndItemFormDTOList = new ArrayList<>();
            for (int i = 0; i < orderDtoList.size(); i++) {
                OrderAndItemFormDTO orderAndItemFormDTO = new OrderAndItemFormDTO();
                orderAndItemFormDTO.setItemFormDTO(itemFormDtoList.get(i));
                orderAndItemFormDTO.setOrderDto(orderDtoList.get(i));
                orderAndItemFormDTOList.add(orderAndItemFormDTO);
            }
            List<AddressDto> addressDtoList = addressService.chooseAddress(email);
            model.addAttribute("addressDtoList", addressDtoList);
            model.addAttribute("orderAndItemFormDTOList", orderAndItemFormDTOList);
            model.addAttribute("memberDto", memberDto);

            return "order/orderpayment";
        } catch (NullPointerException e) {
            e.printStackTrace();
            return "redirect:/";
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "member/login";
        }

    }


    @PostMapping("/orders/form")        // /orders/form으로 값을 넘겨주는 역할
    public @ResponseBody ResponseEntity orderSend(@RequestBody List<OrderDto> orderDto, HttpSession session) {
        try {
            session.setAttribute("orderDto", orderDto);
            System.out.println("세션에 잘 저장되어있는지 확인: " + orderDto.toString());
            return ResponseEntity.ok("완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("오류입니다.");
        }
    }

    @PostMapping("/orders/pay")
    public @ResponseBody ResponseEntity orderPay(@RequestBody List<OrderDto> orderDto, HttpSession session) {

        Long orderId;
        try {
            String email = (String) session.getAttribute("loginEmail");
            orderId = orderService.orders(orderDto, email);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }

    @GetMapping("/orders/completion/{id}")
    public String paymentCompleted(@PathVariable("id") Long id, Model model, HttpSession session) {
        String email = (String) session.getAttribute("loginEmail");
        MemberEntity member = memberRepository.findByMemberEmail(email)
                .orElseThrow(EntityNotFoundException::new);
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        OrderHistDto orderHistDto = new OrderHistDto(order);
        model.addAttribute("order", orderHistDto);
        model.addAttribute("member", MemberDTO.toMemberDTO(member));
        return "order/paymentcompleted";
    }

    @GetMapping(value = {"/orders/list", "/orders/list/{page}"})
    public String orderList(@PathVariable("page") Optional<Integer> page,
                            @RequestParam(value = "searchQuery", required = false, defaultValue = "") String searchQuery,
                            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDateString,
                            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDateString,
                            HttpSession session, Model model) {
        String email = (String) session.getAttribute("loginEmail");
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        LocalDate defaultStartDate = LocalDate.now().minusYears(1);
        LocalDateTime startDate = startDateString != null ? startDateString.atStartOfDay() : defaultStartDate.atStartOfDay();
        LocalDate defaultEndDate = LocalDate.now();
        LocalDateTime endDate = endDateString != null ? endDateString.atStartOfDay().plusDays(1).minusNanos(1) : defaultEndDate.atStartOfDay().plusDays(1).minusNanos(1);

        Page<OrderHistDto> orderHistDtoList =
                orderService.getOrderList(email, searchQuery, startDate, endDate, pageable);
        model.addAttribute("orders", orderHistDtoList);
        model.addAttribute("searchQuery", searchQuery);
        model.addAttribute("startDate", startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        model.addAttribute("endDate", endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("maxPage", 5);


        return "order/orderlist";
    }

    @DeleteMapping("orders/cancel/{orderId}")
    public String orderCancel(@PathVariable("orderId") Long orderId) {

        orderRepository.deleteById(orderId);
        return "redirect:/";

    }

}