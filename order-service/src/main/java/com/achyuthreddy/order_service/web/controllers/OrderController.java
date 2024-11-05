package com.achyuthreddy.order_service.web.controllers;

import com.achyuthreddy.order_service.domain.OrderService;
import com.achyuthreddy.order_service.domain.SecurityService;
import com.achyuthreddy.order_service.domain.models.*;
import jakarta.validation.Valid;
import java.util.List;
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

    @GetMapping
    List<OrderSummary> getOrders() {
        String username = securityService.getLoginUser();
        log.info("Fetching orders for user: {}", username);
        return orderService.findOrders(username);
    }

    @GetMapping(value = "/{orderNumber}")
    OrderDTO getOrder(@PathVariable(value = "orderNumber") String orderNumber) {
        log.info("Fetching order by id: {}", orderNumber);
        String userName = securityService.getLoginUser();
        return orderService
                .findUserOrder(userName, orderNumber)
                .orElseThrow(() -> new OrderNotFoundException(orderNumber));
    }
}
