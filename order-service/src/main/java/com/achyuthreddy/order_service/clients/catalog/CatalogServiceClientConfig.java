package com.achyuthreddy.order_service.clients.catalog;

import com.achyuthreddy.order_service.ApplicationProperties;

import java.time.Duration;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;



@Configuration
public class CatalogServiceClientConfig {
    @Bean
    RestClient restClient(ApplicationProperties properties) {
        return RestClient.builder().baseUrl(properties.catalogServiceUrl())
                .requestFactory(ClientHttpRequestFactories.get(ClientHttpRequestFactorySettings.DEFAULTS
                        .withConnectTimeout(Duration.ofSeconds(5)) // Max time that can be taken to establish connection
                        .withReadTimeout(Duration.ofSeconds(5)))) // Time out for receiving response from service.
                .build();
    }
}
