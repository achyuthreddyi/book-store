package com.achyuthreddy.order_service.testdata;

import static org.instancio.Select.field;

import com.achyuthreddy.order_service.domain.models.Address;
import com.achyuthreddy.order_service.domain.models.CreateOrderRequest;
import com.achyuthreddy.order_service.domain.models.Customer;
import com.achyuthreddy.order_service.domain.models.OrderItem;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import org.instancio.Instancio;

public class TestDataFactory {
    static final List<String> VALID_COUNTRIES = List.of("India", "Germany");
    static final Set<OrderItem> VALID_ORDER_ITEMS =
            Set.of(new OrderItem("P100", "Product 1", new BigDecimal("25.50"), 1));
    static final Set<OrderItem> INVALID_ORDER_ITEMS =
            Set.of(new OrderItem("NOTINDBRECORDS", "Product1", new BigDecimal("25.90"), 1));

    public static CreateOrderRequest createValidOrderRequest() {
        return Instancio.of(CreateOrderRequest.class)
                .generate(field(Customer::email), gen -> gen.text().pattern("#a#a#a#a#a#a@mail.com"))
                .set(field(CreateOrderRequest::items), VALID_ORDER_ITEMS)
                .generate(field(Address::country), gen -> gen.oneOf(VALID_COUNTRIES))
                .create();
    }

    public static CreateOrderRequest createOrderRequestWithInvalidCustomer() {
        return Instancio.of(CreateOrderRequest.class)
                .generate(field(Customer::email), gen -> gen.text().pattern("#c#c#c#c#d#d@mail.com"))
                .set(field(Customer::phone), "")
                .generate(field(Address::country), gen -> gen.oneOf(VALID_COUNTRIES))
                .set(field(CreateOrderRequest::items), VALID_ORDER_ITEMS)
                .create();
    }

    public static CreateOrderRequest createOrderRequestWithInvalidDeliveryAddress() {
        return Instancio.of(CreateOrderRequest.class)
                .generate(field(Customer::email), gen -> gen.text().pattern("#c#c#c#c#d#d@mail.com"))
                .set(field(Address::country), "")
                .set(field(CreateOrderRequest::items), VALID_ORDER_ITEMS)
                .create();
    }

    public static CreateOrderRequest createOrderRequestWithNoItems() {
        return Instancio.of(CreateOrderRequest.class)
                .generate(field(Customer::email), gen -> gen.text().pattern("#c#c#c#c#d#d@mail.com"))
                .generate(field(Address::country), gen -> gen.oneOf(VALID_COUNTRIES))
                .set(field(CreateOrderRequest::items), Set.of())
                .create();
    }
}
