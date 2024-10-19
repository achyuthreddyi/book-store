package com.achyuthreddyyi.catalog_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CatalogServiceApplication {

    public static void main(String[] args) {
        System.out.println("Starting Catalog Service Application...");

        SpringApplication.run(CatalogServiceApplication.class, args);
    }
}
