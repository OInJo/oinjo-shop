package kr.idu.OInjo_Shop.service.Cart;

import kr.idu.OInjo_Shop.dto.Cart.CartDTO;
import kr.idu.OInjo_Shop.dto.Cart.CartItemDTO;
import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.dto.Order.OrderDto;
import kr.idu.OInjo_Shop.dto.Order.OrdersDto;
import kr.idu.OInjo_Shop.entity.Cart.CartEntity;
import kr.idu.OInjo_Shop.entity.Cart.CartItemEntity;
import kr.idu.OInjo_Shop.entity.Item.ItemEntity;
import kr.idu.OInjo_Shop.entity.Item.ItemImgEntity;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import kr.idu.OInjo_Shop.repository.Cart.CartItemRepository;
import kr.idu.OInjo_Shop.repository.Cart.CartRepository;
import kr.idu.OInjo_Shop.repository.Member.MemberRepository;
import kr.idu.OInjo_Shop.service.Item.ItemImg.ItemImgServiceImpl;
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
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemImgServiceImpl itemImgService;
    private final OrderService orderService;
    private final MemberRepository memberRepository;

    //장바구니 생성
    @Override
    public void createCart(MemberDTO member) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(member);
        CartEntity cart = CartEntity.createCart(memberEntity);
        cartRepository.save(cart);
    }


    //장바구니에 상품 추가
    @Override
    public void addCart(MemberDTO member, ItemFormDTO item, Integer count) {
        MemberEntity memberEntity = memberRepository.findById(member.getId()).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        CartEntity cart = cartRepository.findByMember(memberEntity);
        ItemImgEntity itemImg = itemImgService.findFirstItemImgByItemId(item.getId());

        if (cart == null) {  //카트가 비어있다면 생성
            cart = CartEntity.createCart(memberEntity);
            cartRepository.save(cart);
        }

        ItemEntity itemEntity = item.createItem();

        // CartItem 생성
        CartItemEntity cartItem = cartItemRepository.findByCartAndProduct(cart, itemEntity);

        //CartItem이 비워져 있다면 새로 생성
        if (cartItem == null) {
            cartItem = CartItemEntity.createCartItem(cart, itemEntity, count, itemImg);
            cartItemRepository.save(cartItem);

            cart.setCount(cart.getCount() + 1);
        } else {    //비어 있지 않다면 상품 갯수 그만큼 추가
            cartItem.addCount(count);
        }
    }

    // 장바구니 조회하기
    @Override
    public List<CartItemDTO> memberCartView(CartDTO cart) {
        List<CartItemEntity> cartItems = cartItemRepository.findAll();
        List<CartItemDTO> memberItems = new ArrayList<>();

        for (CartItemEntity cartItem : cartItems) {
            if (cartItem.getCart() != null && cart != null && cartItem.getCart().getId().equals(cart.getId())) {
                CartItemDTO cartItemDTO = CartItemDTO.toCartItemDTO(cartItem);
                memberItems.add(cartItemDTO);
            }
        }

        return memberItems;
    }


    // 장바구니 특정 상품 갯수 수정하기
    @Override
    public boolean updateItemCount(Long id, Long itemId, Integer count) {
        Optional<CartItemEntity> cartItemCnt = cartItemRepository.findById(itemId);
        if (cartItemCnt.isPresent()) {
            CartItemEntity cartItem = cartItemCnt.get();

            if (cartItem.getCart().getMember().getId().equals(id)) {
                cartItem.setCount(count);
                cartItemRepository.save(cartItem);
                return true;
            }
        }
        return false;
    }

    //장바구니 상품 삭제하기
    @Override
    public void cartItemDelete(Long id) {
        cartItemRepository.deleteById(id);

    }

    //장바구니 비우기
    @Override
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
    @Override
    public void cartDelete(Long id) {
        allCartItemDelete(id);
        cartRepository.deleteById(id);
    }

    @Override
    public Long cartOrders(List<OrdersDto> ordersDtoList, String email) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (OrdersDto ordersDto : ordersDtoList) {
            CartItemEntity cartItem = cartItemRepository.findById(ordersDto.getCartItemId())
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
