package kr.idu.OInjo_Shop.service.Cart;

import kr.idu.OInjo_Shop.dto.Order.OrderDto;
import kr.idu.OInjo_Shop.dto.Order.OrdersDto;
import kr.idu.OInjo_Shop.entity.Cart.CartEntity;
import kr.idu.OInjo_Shop.entity.Cart.CartItemEntity;
import kr.idu.OInjo_Shop.entity.Item.ItemEntity;
import kr.idu.OInjo_Shop.entity.Item.ItemImgEntity;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import kr.idu.OInjo_Shop.repository.Cart.CartItemRepository;
import kr.idu.OInjo_Shop.repository.Cart.CartRepository;
import kr.idu.OInjo_Shop.service.Item.ItemImgService;
import kr.idu.OInjo_Shop.service.Order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemImgService itemImgService;
    private final OrderService orderService;

    //장바구니 생성
    public void createCart(MemberEntity member) {
        CartEntity cart = CartEntity.creatCart(member);
        cartRepository.save(cart);
    }

    //장바구니에 상품 추가
    public void addCart(MemberEntity member, ItemEntity item, Integer count) {
        CartEntity cart = cartRepository.findByMember(member);
        ItemImgEntity itemImg = itemImgService.findFirstItemImgByItemId(item.getItemId());

        if(cart == null) {  //카트가 비어있다면 생성
            cart = CartEntity.creatCart(member);
            cartRepository.save(cart);
        }

        // CartItem 생성
        CartItemEntity cartItem = cartItemRepository.findByCartAndProduct(cart, item);

        //CartItem이 비워져 있다면 새로 생성
        if(cartItem == null) {
            cartItem = CartItemEntity.createCartItem(cart, item ,count, itemImg);
            cartItemRepository.save(cartItem);
            cart.setCount(cart.getCount() + 1);
        } else {    //비어 있지 않다면 상품 갯수 그만큼 추가
            cartItem.addCount(count);
        }
    }

    // 장바구니 조회하기
    public List<CartItemEntity> memberCartView(CartEntity cart) {
        List<CartItemEntity> cartItems = cartItemRepository.findAll();
        List<CartItemEntity> memberItems = new ArrayList<>();
        for(CartItemEntity cartItem : cartItems ) {
            if (cartItem.getCart() != null && cart != null && cartItem.getCart().getId().equals(cart.getId())) {
                memberItems.add(cartItem);
            }

        }
        return memberItems;
    }

    // 장바구니 특정 상품 갯수 수정하기
    public boolean updateItemCount(Long id, Long itemId, Integer count) {
        Optional<CartItemEntity> cartItemCnt = cartItemRepository.findById(itemId);
        if(cartItemCnt.isPresent()) {
            CartItemEntity cartItem = cartItemCnt.get();

            if(cartItem.getCart().getMember().getId().equals(id)) {
                cartItem.setCount(count);
                cartItemRepository.save(cartItem);
                return true;
            }
        }
        return false;
    }

    //장바구니 상품 삭제하기
    public void cartItemDelete(Long id) {
        cartItemRepository.deleteById(id);
    }

    public boolean allCartItemDelete(Long id) {
        List<CartItemEntity> cartItems = cartItemRepository.findAll();

        // 해당 멤버의 상품을 찾아 모두 삭제
        boolean deleted = false;
        for (CartItemEntity cartItem : cartItems) {
            if (Objects.equals(cartItem.getCart().getMember().getId(), id)) {
                cartItem.getCart().setCount(cartItem.getCart().getCount() - 1);
                cartItemRepository.deleteById(cartItem.getId());
                deleted = true;
            }
        }

        // 삭제 여부를 반환
        return deleted;
    }


    //장바구니 삭제
    public void cartDelete(Long id) {
        allCartItemDelete(id);
        cartRepository.deleteById(id);
    }

//    //장바구니 결제     //Entitiy에 없는 필드가 있어서 일단은 주석 처리 나중에 구현시 참고용도
//    @Transactional
//    public void cartPayment(long id) {
//        List<CartItemEntity> cartItems = cartItemRepository.findAll();
//        List<CartItemEntity> memberCartItems = new ArrayList<>();
//        MemberEntity buyer = memberRepository.findById(id).get();
//
//        //반복문으로 접속 member의 상품만 찾아서 저장
//        for(CartItemEntity cartItem : cartItems) {
//            if(cartItem.getCart().getMember().getId() == buyer.getId()) {
//                memberCartItems.add(cartItem);
//            }
//        }
//
//        //반복문으로 접속 member의 상품만 찾아서 삭제
//        for(CartItemEntity cartItem : memberCartItems) {
//            //재고 변경
//            int stock = cartItem.getProduct().getProductStock();  //현재 재고를 변수에 저장
//            stock = stock - cartItem.getCount();    //저장된 변수에 결제한 변수만큼 빼서 재고에 반영
//            cartItem.getProduct().setProductStock(stock); //재고 갯수 변경
//
//            //금액 처리
//            MemberEntity seller = cartItem.getProduct().getMember();
//            int cash = cartItem.getProduct().getProductPrice();   //아이템 가격을 변수에 저장
//            buyer.setMoney(cash * -1);    //이 기능은 Member 생성 시 금액을 지정하고 상품 만큼 가격을 빼는 기능이라
//            seller.setMoney(cash);        //일단은 주석처리로 결제 기능을 주석처리
//        }


    public Long cartOrders(List<OrdersDto> ordersDtoList, String email) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (OrdersDto ordersDto : ordersDtoList) {
            CartItemEntity cartItem=cartItemRepository.findById(ordersDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            OrderDto orderDto = new OrderDto();
            orderDto.setItemId(cartItem.getProduct().getItemId());
            orderDto.setCount(cartItem.getCount());
            orderDtoList.add(orderDto);
        }
        Long orderId = orderService.orders(orderDtoList, email);
        return orderId;
    }
}
