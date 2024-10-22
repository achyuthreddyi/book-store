package com.achyuthreddy.order_service.clients.catalog;

import com.achyuthreddy.order_service.ApplicationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class CatalogServiceClientCatalog {
    @Bean
    RestClient restClient(ApplicationProperties properties) {
        return RestClient.builder().baseUrl(properties.catalogServiceUrl()).build();
    }
}
