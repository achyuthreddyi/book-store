package com.achyuthreddy.order_service.domain;

import com.achyuthreddy.order_service.domain.models.OrderStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByStatus(OrderStatus orderStatus);

    default void updateOrderStatus(String orderNumber, OrderStatus orderStatus) {
        OrderEntity order = this.findByOrderNumber(orderNumber).orElseThrow();
        order.setStatus(orderStatus);
        this.save(order);
    }

    Optional<OrderEntity> findByOrderNumber(String orderNumber);
}
