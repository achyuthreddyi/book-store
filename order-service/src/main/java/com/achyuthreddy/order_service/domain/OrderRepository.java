package com.achyuthreddy.order_service.domain;

import com.achyuthreddy.order_service.domain.models.OrderStatus;
import com.achyuthreddy.order_service.domain.models.OrderSummary;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByStatus(OrderStatus orderStatus);

    default void updateOrderStatus(String orderNumber, OrderStatus orderStatus) {
        OrderEntity order = this.findByOrderNumber(orderNumber).orElseThrow();
        order.setStatus(orderStatus);
        this.save(order);
    }

    Optional<OrderEntity> findByOrderNumber(String orderNumber);

    @Query(
            """
                SELECT new com.achyuthreddy.order_service.domain.models.OrderSummary (o.orderNumber, o.status)
                from OrderEntity o
                where o.userName = :userName
           """)
    List<OrderSummary> findByUserName(String userName);

    @Query(
            """
        select distinct o
        from OrderEntity o left join fetch o.items
        where o.userName = :userName and o.orderNumber = :orderNumber
        """)
    Optional<OrderEntity> findByUseNameAndOrderNumber(String userName, String orderNumber);
}
