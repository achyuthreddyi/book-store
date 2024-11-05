package com.achyuthreddy.order_service.domain.models;

import java.time.LocalDateTime;
import java.util.Set;

public record OrderDTO(
        String orderNumber,
        String user,
        Set<OrderItem> items,
        Customer customer,
        Address address,
        OrderStatus status,
        String comments,
        LocalDateTime createdAt) {}
