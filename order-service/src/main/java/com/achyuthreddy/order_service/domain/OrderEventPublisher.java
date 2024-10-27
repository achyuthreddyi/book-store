package com.achyuthreddy.order_service.domain;

import com.achyuthreddy.order_service.ApplicationProperties;
import com.achyuthreddy.order_service.domain.models.OrderCancelledEvent;
import com.achyuthreddy.order_service.domain.models.OrderCreatedEvent;
import com.achyuthreddy.order_service.domain.models.OrderDeliveredEvent;
import com.achyuthreddy.order_service.domain.models.OrderErrorEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties properties;

    public OrderEventPublisher(RabbitTemplate rabbitTemplate, ApplicationProperties properties) {
        this.rabbitTemplate = rabbitTemplate;
        this.properties = properties;
    }

    public void publish(OrderCreatedEvent orderCreatedEvent) {
        this.send(properties.newOrdersQueue(), orderCreatedEvent);
    }

    public void publish(OrderDeliveredEvent orderDeliveredEvent) {
        this.send(properties.deliveredOrdersQueue(), orderDeliveredEvent);
    }

    public void publish(OrderCancelledEvent orderCancelledEvent) {
        this.send(properties.cancelledOrdersQueue(), orderCancelledEvent);
    }

    public void publish(OrderErrorEvent orderErrorEvent) {
        this.send(properties.errorOrdersQueue(), orderErrorEvent);
    }

    private void send(String routingKey, Object payload) {
        rabbitTemplate.convertAndSend(properties.orderEventsExchange(), routingKey, payload);
    }
}
