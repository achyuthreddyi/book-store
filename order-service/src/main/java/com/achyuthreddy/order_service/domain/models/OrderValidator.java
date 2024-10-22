package com.achyuthreddy.order_service.domain.models;

import com.achyuthreddy.order_service.clients.catalog.Product;
import com.achyuthreddy.order_service.clients.catalog.ProductServiceClient;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {
    private static final Logger logger = LoggerFactory.getLogger(OrderValidator.class);

    private final ProductServiceClient client;

    OrderValidator(ProductServiceClient client) {
        this.client = client;
    }

    public void validate(CreateOrderRequest request) {
        Set<OrderItem> items = request.items();

        for (OrderItem item : items) {
            Product product = client.getProductByCode(item.code())
                    .orElseThrow(() -> new InvalidOrderException("Invalid Product Code: " + item.code()));

            if (item.price().compareTo(product.price()) != 0) {
                logger.error(
                        "Product price not matching. Actual price: {}, recevied proce: {}",
                        product.price(),
                        item.price());
                throw new InvalidOrderException("Product price not matching");
            }
        }
    }
}
