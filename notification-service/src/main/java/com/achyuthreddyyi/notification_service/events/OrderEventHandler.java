package com.achyuthreddyyi.notification_service.events;

import com.achyuthreddyyi.notification_service.domain.NotificationService;
import com.achyuthreddyyi.notification_service.domain.OrderEventEntity;
import com.achyuthreddyyi.notification_service.domain.OrderEventRepository;
import com.achyuthreddyyi.notification_service.domain.models.OrderCancelledEvent;
import com.achyuthreddyyi.notification_service.domain.models.OrderCreatedEvent;
import com.achyuthreddyyi.notification_service.domain.models.OrderDeliveredEvent;
import com.achyuthreddyyi.notification_service.domain.models.OrderErrorEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {
    private final NotificationService notificationService;
    private final OrderEventRepository orderEventRepository;
    private final Logger log = LoggerFactory.getLogger(OrderEventHandler.class);

    OrderEventHandler(NotificationService notificationService, OrderEventRepository orderEventRepository) {
        this.notificationService = notificationService;
        this.orderEventRepository = orderEventRepository;
    }

    @RabbitListener(queues = "${notifications.new-orders-routing-key}")
    void handleOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("Order Created Event: " + event);
        if (orderEventRepository.existsByEventId(event.eventId())) {
            log.warn("Received duplicate Order Created Event with eventId: {} already exists", event.eventId());
            return;
        }
        notificationService.sendOrderCreatedNotification(event);
        OrderEventEntity orderEventEntity = new OrderEventEntity(event.eventId());
        orderEventRepository.save(orderEventEntity);
    }

    @RabbitListener(queues = "${notifications.delivered-orders-routing-key}")
    void handleOrderDeletedEvent(OrderDeliveredEvent event) {
        log.info("Order Created Event: " + event);
        if (orderEventRepository.existsByEventId(event.eventId())) {
            log.warn("Received duplicate Order Deleted Event with eventId: {} already exists", event.eventId());
            return;
        }
        notificationService.sendOrderDeliveredNotification(event);
        OrderEventEntity orderEventEntity = new OrderEventEntity(event.eventId());
        orderEventRepository.save(orderEventEntity);
    }

    @RabbitListener(queues = "${notifications.cancelled-orders-queue}")
    void handleOrderCancelledEvent(OrderCancelledEvent event) {
        log.info("Order Created Event: " + event);
        if (orderEventRepository.existsByEventId(event.eventId())) {
            log.warn("Received duplicate Order Cancelled Event with eventId: {} already exists", event.eventId());
            return;
        }
        notificationService.sendOrderCancelledNotification(event);
        OrderEventEntity orderEventEntity = new OrderEventEntity(event.eventId());
        orderEventRepository.save(orderEventEntity);
    }

    @RabbitListener(queues = "${notifications.error-orders-queue}")
    void handleOrderErrorEvent(OrderErrorEvent event) {
        log.info("Order Created Event: " + event);
        if (orderEventRepository.existsByEventId(event.eventId())) {
            log.warn("Received duplicate Order Error Event with eventId: {} already exists", event.eventId());
            return;
        }
        notificationService.sendOrderErrorEventNotification(event);
        OrderEventEntity orderEventEntity = new OrderEventEntity(event.eventId());
        orderEventRepository.save(orderEventEntity);
    }
}
