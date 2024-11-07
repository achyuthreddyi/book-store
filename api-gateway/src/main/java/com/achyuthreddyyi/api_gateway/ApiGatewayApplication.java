package com.achyuthreddyyi.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        System.out.println("API gateway");
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
