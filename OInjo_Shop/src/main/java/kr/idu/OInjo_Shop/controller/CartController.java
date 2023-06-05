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

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CartController {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartService cartService;
    private final MemberService memberService;
    private final ItemService itemService;
    private final MemberRepository memberRepository;

    // 내 장바구니 조회
    @GetMapping("/member/{id}/cart")
    public String myCartPage(@PathVariable("id") Long id, Model model, Authentication authentication){
        MemberDTO member = (MemberDTO) authentication.getPrincipal();
        // 로그인 Member == 접속 Member
        if(member.getId() == id) {
            // Member의 장바구니를 가져온다.
            CartEntity cart = cartRepository.findByMember(member);

            // 장바구니의 아이템을 가져온다.
            List<CartItemEntity> cartItems = cartService.memberCartView(cart);

            int totalPrice = 0;
            for(CartItemEntity cartItem : cartItems){
                totalPrice += ( cartItem.getProduct().getProductPrice() * cartItem.getCount());
            }

            model.addAttribute("cartItemList",cartItems);
            model.addAttribute("totalPrice",totalPrice);
            model.addAttribute("member",memberService.findById(id));

            return "/cart";
        }else{
            return "redirect:/";
        }
    }

    //장바구니에 상품 추가
    @PostMapping("/member/{id}/cart/{itemId}")
    public String myCartAdd(@PathVariable("id") Long id, @PathVariable("itemId") Long itemId, int count){
        MemberDTO member = MemberDTO.toMemberDTO(MemberEntity.toMemberEntity(memberService.findById(id)));
        ItemFormDTO item = ItemFormDTO.of(itemService.itemView(itemId));

        cartService.addCart(member, item, count);

        return "redirect:/item/view/{itemId}";
    }

    //장바구니에서 특정 상품 삭제
    @GetMapping("/member/{id}/cart/{cartItemId}/delete")
    public String myCartDelete(@PathVariable("id") Long id, @PathVariable("cartItemId") Long cartItemId){
        MemberDTO member = memberService.findById(id);
        CartEntity cart = cartRepository.findByMember(member);
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
