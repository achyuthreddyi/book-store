package com.achyuthreddy.order_service.jobs;

import com.achyuthreddy.order_service.domain.OrderService;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessingJob {
    private static final Logger log = LoggerFactory.getLogger(OrderProcessingJob.class);

    private final OrderService orderService;

    public OrderProcessingJob(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(cron = "${orders.new-order-job-cron}")
    public void processNeOrders() {
        log.info("Processing new orders as {}", Instant.now());
        orderService.processNewOrders();
    }
}
