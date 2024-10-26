package com.achyuthreddy.order_service.domain;

import com.achyuthreddy.order_service.domain.models.OrderCreatedEvent;
import com.achyuthreddy.order_service.domain.models.OrderEventType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderEventService {
    private static final Logger log = LoggerFactory.getLogger(OrderEventService.class);

    private final OrderEventRepository orderEventRepository;
    private final ObjectMapper objectMapper;
    public OrderEventService(OrderEventRepository orderEventRepository, ObjectMapper objectMapper) {
        this.orderEventRepository = orderEventRepository;
        this.objectMapper = objectMapper;
    }

    void save(OrderCreatedEvent event){
        OrderEventEntity orderEvent = new OrderEventEntity();
        orderEvent.setEventId(event.eventId());
        orderEvent.setEventType(OrderEventType.ORDER_CREATED);
        orderEvent.setOrderNumber(event.orderNumber());
        orderEvent.setCreatedAt(event.createdAt());
        orderEvent.setPayload(toJsonPayload(event));
        orderEventRepository.save(orderEvent);
    }

    private String toJsonPayload(Object object){
        try{
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e){
            throw new RuntimeException();
        }
    }
}
