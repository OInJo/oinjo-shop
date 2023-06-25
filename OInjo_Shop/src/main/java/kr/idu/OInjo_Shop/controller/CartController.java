package kr.idu.OInjo_Shop.controller;


import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.entity.Cart.CartEntity;
import kr.idu.OInjo_Shop.entity.Cart.CartItemEntity;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import kr.idu.OInjo_Shop.repository.Cart.CartItemRepository;
import kr.idu.OInjo_Shop.repository.Cart.CartRepository;
import kr.idu.OInjo_Shop.repository.Member.MemberRepository;
import kr.idu.OInjo_Shop.service.Cart.CartService;
import kr.idu.OInjo_Shop.service.Item.ItemService;
import kr.idu.OInjo_Shop.service.Member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class CartController {

    HttpSession session = null;

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartService cartService;
    private final MemberService memberService;
    private final ItemService itemService;
    private final MemberRepository memberRepository;

    // 내 장바구니 조회
    @GetMapping("/member/{id}/cart")
    public String myCartPage(@PathVariable("id") Long id, Model model, HttpServletRequest request){
        session = request.getSession();
        Long memberId = (Long) session.getAttribute("loginId");

        // 로그인 Member == 접속 Member
        if(Objects.equals(id, memberId)) {
            // Member의 장바구니를 가져온다.
            MemberEntity member = MemberEntity.toMemberEntity(memberService.findById(id));
            CartEntity cart = cartRepository.findByMember(member.getId());

            // 장바구니의 아이템을 가져온다.
            List<CartItemEntity> cartItems = cartService.memberCartView(cart);

            int totalPrice = 0;
            for(CartItemEntity cartItem : cartItems){
                totalPrice += ( cartItem.getProduct().getItemPrice() * cartItem.getCount());
            }

            model.addAttribute("cartItemList",cartItems);
            model.addAttribute("totalPrice",totalPrice);
            model.addAttribute("member", member);

            return "/cart/cart";
        } else {
            return "redirect:/";
        }
    }



    //장바구니에 상품 추가
    @PostMapping("/member/{id}/cart/{itemId}")
    public String myCartAdd(@PathVariable("id") Long id, @PathVariable("itemId") Long itemId, int count){
        ItemFormDTO item = ItemFormDTO.of(itemService.findItemId(itemId));

        cartService.addCart(id, item, count);

        return "redirect:/item/view/{itemId}";
    }

    //장바구니에서 특정 상품 삭제
    @GetMapping("/member/{id}/cart/{cartItemId}/delete")
    public String myCartDelete(@PathVariable("id") Long id, @PathVariable("cartItemId") Long cartItemId){
        CartEntity cart = cartRepository.findByMember(id);
        cart.setCount(cart.getCount() - 1);
        cartService.cartItemDelete(cartItemId);

        return "redirect:/member/{id}/cart";
    }

    //결제 페이지
    @PostMapping("/member/{id}/cart/checkout")
    public String myCartPayment(@PathVariable("id") Long id, Model model){
        MemberEntity member = MemberEntity.toMemberEntity(memberService.findById(id));
        //cartService.cartPayment(id); // 결제처리
        cartService.cartDelete(id); // 장바구니 비우기

        return "redirect:/";
    }



}
