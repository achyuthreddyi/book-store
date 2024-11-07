package com.achyuthreddyyi.notification_service.events;

import com.achyuthreddyyi.notification_service.domain.NotificationService;
import com.achyuthreddyyi.notification_service.domain.models.OrderCancelledEvent;
import com.achyuthreddyyi.notification_service.domain.models.OrderCreatedEvent;
import com.achyuthreddyyi.notification_service.domain.models.OrderDeliveredEvent;
import com.achyuthreddyyi.notification_service.domain.models.OrderErrorEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {
    private final NotificationService notificationService;

    OrderEventHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "${notifications.new-orders-routing-key}")
    void handleOrderCreatedEvent(OrderCreatedEvent event) {
        System.out.println("Order Created Event: " + event);
        notificationService.sendOrderCreatedNotification(event);
    }

    @RabbitListener(queues = "${notifications.delivered-orders-routing-key}")
    void handleOrderDeletedEvent(OrderDeliveredEvent event) {
        System.out.println("Order Created Event: " + event);
        notificationService.sendOrderDeliveredNotification(event);
    }

    @RabbitListener(queues = "${notifications.cancelled-orders-queue}")
    void handleOrderCancelledEvent(OrderCancelledEvent event) {
        System.out.println("Order Created Event: " + event);
        notificationService.sendOrderCancelledNotification(event);
    }

    @RabbitListener(queues = "${notifications.error-orders-queue}")
    void handleOrderErrorEvent(OrderErrorEvent event) {
        System.out.println("Order Created Event: " + event);
        notificationService.sendOrderErrorEventNotification(event);
    }
}
