package kr.idu.OInjo_Shop.repository.Order;

import kr.idu.OInjo_Shop.entity.Order.OrderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("select  o from OrderEntity o " +
            "where o.member.memberEmail = :email " +
            "order by o.orderDate desc"
    )
    List<OrderEntity> findOrders(@Param("email") String email, Pageable pageable);


    @Query("select count(o) from OrderEntity o " +
            "where o.member.memberEmail = :email"
    )
    Long totalOrder(@Param("email") String email);


    @Query("select o from OrderEntity o " +
            "join o.orderItems oi " +
            "join oi.item i " +
            "where o.member.memberEmail = :email " +
            "and ((:searchQuery = '' or :searchQuery is null) or i.brand.brandName LIKE %:searchQuery% or i.itemName LIKE %:searchQuery%) " +
            "and (:startDate is null or o.orderDate >= :startDate) and (:endDate is null or o.orderDate <= :endDate) " +
            "order by o.orderDate desc")
    List<OrderEntity> findOrdersWithSearch(@Param("email") String email,
                                           @Param("searchQuery") String searchQuery,
                                           @Param("startDate") LocalDateTime startDate,
                                           @Param("endDate") LocalDateTime endDate,
                                           Pageable pageable);

    @Query("select count(o) from OrderEntity o " +
            "join o.orderItems oi " +
            "join oi.item i " +
            "where o.member.memberEmail = :email " +
            "and (:startDate is null or o.orderDate >= :startDate) and (:endDate is null or o.orderDate <= :endDate) " +
            "and (:searchQuery = '' or i.brand.brandName LIKE %:searchQuery% or i.itemName LIKE %:searchQuery%) "
    )
    Long totalOrderItem(@Param("email") String email,
                        @Param("searchQuery") String searchQuery,
                        @Param("startDate") LocalDateTime startDate,
                        @Param("endDate") LocalDateTime endDate);
}
