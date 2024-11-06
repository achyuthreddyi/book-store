package com.achyuthreddyyi.notification_service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "notifications")
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
