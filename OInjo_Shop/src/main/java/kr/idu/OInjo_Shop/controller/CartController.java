package kr.idu.OInjo_Shop.controller;


import kr.idu.OInjo_Shop.dto.Cart.CartDTO;
import kr.idu.OInjo_Shop.dto.Cart.CartItemDTO;
import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.dto.Order.OrdersDto;
import kr.idu.OInjo_Shop.entity.Cart.CartEntity;
import kr.idu.OInjo_Shop.repository.Cart.CartItemRepository;
import kr.idu.OInjo_Shop.repository.Cart.CartRepository;
import kr.idu.OInjo_Shop.repository.Member.MemberRepository;
import kr.idu.OInjo_Shop.service.Cart.CartServiceImpl;
import kr.idu.OInjo_Shop.service.Item.ItemServiceImpl;
import kr.idu.OInjo_Shop.service.Member.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Controller
@Transactional
public class CartController {

    HttpSession session = null;

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartServiceImpl cartServiceImpl;
    private final MemberServiceImpl memberServiceImpl;
    private final ItemServiceImpl itemServiceImpl;
    private final MemberRepository memberRepository;

    @GetMapping("/member/{id}/cart")
    public String myCartPage(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute("loginId");

        // 로그인 Member == 접속 Member
        if (Objects.equals(id, memberId)) {
            System.out.println("여기로는 들어와 질거고");
            // Member의 장바구니를 가져온다.
            MemberDTO member = memberServiceImpl.findById(id);
            CartDTO cartDTO = CartDTO.toCartDTO(cartRepository.findByMemberId(member.getId()));

            // 장바구니의 아이템을 가져온다.
            List<CartItemDTO> cartItems = cartServiceImpl.memberCartView(cartDTO);

            int totalPrice = 0;
            for (CartItemDTO cartItem : cartItems) {
                totalPrice += (cartItem.getProduct().getItemPrice() * cartItem.getCount());
            }

            model.addAttribute("cartItemList", cartItems);
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("member", memberServiceImpl.findByMember(id));

            return "/cart/cart";
        } else {
            return "redirect:/";
        }
    }




    //장바구니에 상품 추가
    @PostMapping("/member/{id}/cart/{itemId}")
    public String myCartAdd(@PathVariable("id") Long id, @PathVariable("itemId") Long itemId, Integer count){
        MemberDTO member = MemberDTO.toMemberDTO(memberServiceImpl.findByMember(id));
        ItemFormDTO item = itemServiceImpl.getItemDetail(itemId);

        cartServiceImpl.addCart(member, item, count);

        return "redirect:/item/{itemId}";
    }

    //장바구니에 있는 특정 상품의 갯수 수정
    @PutMapping("/member/{id}/cart/{cartItemId}/update")
    public String updateItemQuantity(@PathVariable("id") Long id, @PathVariable("cartItemId") Long itemId, Integer count) {
        cartServiceImpl.updateItemCount(id, itemId, count);

        return "redirect:/member/{id}/cart";
    }

    //장바구니에서 특정 상품 삭제
    @GetMapping("/member/{id}/cart/{cartItemId}/delete")
    public String myCartItemDelete(@PathVariable("id") Long id, @PathVariable("cartItemId") Long cartItemId){
        CartEntity cart = cartRepository.findByMemberId(id);
        cart.setCount(cart.getCount() - 1);
        cartServiceImpl.cartItemDelete(cartItemId);

        return "redirect:/member/{id}/cart";
    }

    //장바구니 전체 삭제
    @GetMapping("/member/{id}/cart/itemDelete")
    public String myCartItemAllDelete(@PathVariable("id") Long id) {
        cartServiceImpl.allCartItemDelete(id);

        return "redirect:/member/{id}/cart";
    }


    //장바구니 삭제
    @DeleteMapping("/member/{id}/cart/delete")
    public String myCartDelete(@PathVariable("id") Long id) {
        cartServiceImpl.cartDelete(id);

        return "redirect:/";
    }




    @PostMapping("/cart/orders")
    public @ResponseBody ResponseEntity orders(@RequestBody OrdersDto ordersDto, HttpSession session) {
        String email = (String) session.getAttribute("loginEmail");
        List<OrdersDto> ordersDtoList = ordersDto.getOrdersDtoList();
        Long orderId = cartServiceImpl.cartOrders(ordersDtoList, email);
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }


}
