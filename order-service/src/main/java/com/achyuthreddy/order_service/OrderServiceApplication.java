package com.achyuthreddy.order_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class OrderServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
