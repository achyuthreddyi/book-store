package com.achyuthreddy.order_service.clients.catalog;

import java.util.Optional;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ProductServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceClient.class);

    private final RestClient restClient;

    ProductServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @Retry(name = "catalog-service")
    public Optional<Product> getProductByCode(String code) {
        logger.info("Fetching product for code: {}", code);
            var product = restClient
                    .get()
                    .uri("/api/products/{code}", code)
                    .retrieve()
                    .body(Product.class);

            return Optional.ofNullable(product);
    }
}
