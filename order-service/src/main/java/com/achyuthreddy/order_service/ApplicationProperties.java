package com.achyuthreddy.order_service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "orders")
public record ApplicationProperties(
        String orderEventsExchange,
        String newOrdersQueue,
        String deliveredOrdersQueue,
        String cancelledOrdersQueue,
        String errorOrdersQueue,
        String newOrdersRoutingKey,
        String deliveredOrdersRoutingKey,
        String cancelledOrdersRoutingKey,
        String errorOrdersRoutingKey) {}
