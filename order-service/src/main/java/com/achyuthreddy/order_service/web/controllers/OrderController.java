package com.achyuthreddy.order_service.web.controllers;

import com.achyuthreddy.order_service.domain.OrderService;
import com.achyuthreddy.order_service.domain.SecurityService;
import com.achyuthreddy.order_service.domain.models.CreateOrderRequest;
import com.achyuthreddy.order_service.domain.models.CreateOrderResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final SecurityService securityService;

    OrderController(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateOrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request) {
        String username = securityService.getLoginUser();

        log.info("Creating order for user: {}", username);

        return orderService.createOrder(username, request);
    }
}
