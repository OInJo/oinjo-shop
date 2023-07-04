package kr.idu.OInjo_Shop.service.Order;

import kr.idu.OInjo_Shop.dto.Order.OrderDto;
import kr.idu.OInjo_Shop.entity.Item.ItemEntity;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import kr.idu.OInjo_Shop.entity.Order.OrderEntity;
import kr.idu.OInjo_Shop.entity.Order.OrderItemEntity;
import kr.idu.OInjo_Shop.repository.Item.ItemRepository;
import kr.idu.OInjo_Shop.repository.Member.MemberRepository;
import kr.idu.OInjo_Shop.repository.Order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional      ////로직을 처리하다가 에러가 발생하였다면, 변경된 데이터를 로직을 수행하기 이전 상태로 콜백 시켜줌.
public class OrderService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    private final OrderRepository orderRepository;

    public Long order(OrderDto orderDto, String email) {
        ItemEntity item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        MemberEntity member = memberRepository.findByMemberEmail(email)
                .orElseThrow(EntityNotFoundException::new);

        List<OrderItemEntity> orderItemList = new ArrayList<>();
        OrderItemEntity orderItem =
                OrderItemEntity.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);

        OrderEntity order = OrderEntity.createOrder(member, orderItemList);
        orderRepository.save(order);
        return order.getId();
    }
}
