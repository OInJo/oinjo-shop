package kr.idu.OInjo_Shop.service.Order;

import com.querydsl.core.types.Order;
import kr.idu.OInjo_Shop.dto.Order.OrderDto;
import kr.idu.OInjo_Shop.dto.Order.OrderHistDto;
import kr.idu.OInjo_Shop.dto.Order.OrderItemDto;
import kr.idu.OInjo_Shop.entity.Item.ItemEntity;
import kr.idu.OInjo_Shop.entity.Item.ItemImgEntity;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import kr.idu.OInjo_Shop.entity.Order.OrderEntity;
import kr.idu.OInjo_Shop.entity.Order.OrderItemEntity;
import kr.idu.OInjo_Shop.repository.Item.ItemImgRepository;
import kr.idu.OInjo_Shop.repository.Item.ItemRepository;
import kr.idu.OInjo_Shop.repository.Member.MemberRepository;
import kr.idu.OInjo_Shop.repository.Order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional      ////로직을 처리하다가 에러가 발생하였다면, 변경된 데이터를 로직을 수행하기 이전 상태로 콜백 시켜줌.
public class OrderService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    private final ItemImgRepository itemImgRepository;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public Page<OrderHistDto> getOrderList(String email, String searchQuery, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        List<OrderEntity> orders = orderRepository.findOrdersWithSearch(email, searchQuery, startDate, endDate, pageable);
        Long totalCount = orderRepository.totalOrderItem(email, searchQuery, startDate, endDate);

        List<OrderHistDto> orderHistDtos = new ArrayList<>();
        for (OrderEntity order : orders) {

            OrderHistDto orderHistDto = new OrderHistDto(order);
            List<OrderItemEntity> orderItems = order.getOrderItems();
            for (OrderItemEntity orderItem : orderItems) {
                ItemImgEntity itemImg = itemImgRepository.findByItemItemId(orderItem.getItem().getItemId()).stream().findFirst().orElse(null);     //가져온 상품 이미지들 중 첫 번째 이미지를 선택하거나, 이미지가 없는 경우 null을 반환
                OrderItemDto orderItemDto = new OrderItemDto(orderItem, itemImg.getImgUrl());
                orderHistDto.addOrderItemDto(orderItemDto);
            }
            orderHistDtos.add(orderHistDto);
        }
        return new PageImpl<OrderHistDto>(orderHistDtos, pageable, totalCount);
    }

    //    @Transactional(readOnly = true)
//    public void cancelOrder(Long orderId) {
//        OrderEntity order = orderRepository.findById(orderId).
//                orElseThrow(EntityNotFoundException::new);
//
//        order.cancelOrder();
//    }
    public Long order(OrderDto orderDto, String email) {
        ItemEntity item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        MemberEntity member = memberRepository.findByMemberEmail(email)
                .orElseThrow(EntityNotFoundException::new);

        List<OrderItemEntity> orderItemList = new ArrayList<>();
        OrderItemEntity orderItem =
                OrderItemEntity.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);       //나중에 장바구니가 만들어졌을때에는 상품을 한번에 결제해야함.

        OrderEntity order = OrderEntity.createOrder(member, orderItemList);
        orderRepository.save(order);
        return order.getId();
    }


    public Long orders(List<OrderDto> orderDtoList, String email) {
        MemberEntity member = memberRepository.findByMemberEmail(email)
                .orElseThrow(EntityNotFoundException::new);
        List<OrderItemEntity> orderItemList = new ArrayList<>();

        for (OrderDto orderDto : orderDtoList) {
            ItemEntity item = itemRepository.findById(orderDto.getItemId())
                    .orElseThrow(EntityNotFoundException::new);
            OrderItemEntity orderItem = OrderItemEntity.createOrderItem(item, orderDto.getCount());
            orderItemList.add(orderItem);
        }
        OrderEntity order = OrderEntity.createOrder(member, orderItemList);
        orderRepository.save(order);
        return order.getId();
    }

    public void deleteMemberById(Long memberId) {
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(EntityNotFoundException::new);
        List<OrderEntity> orders = orderRepository.findByMember(member);
        for (OrderEntity order : orders) {
            orderRepository.deleteById(order.getId());
        }
    }
}
