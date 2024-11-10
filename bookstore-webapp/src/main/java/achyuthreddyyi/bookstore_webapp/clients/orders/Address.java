package achyuthreddyyi.bookstore_webapp.clients.orders;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

public record Address(
        @NotBlank(message = "AddressLine1 is required") String addressLine1,
        String addressLine2,
        @NotBlank(message = "City is required") String city,
        @NotBlank(message = "State is required") String state,
        @NotBlank(message = "ZipCode is required") String zipCode,
        @NotBlank(message = "Country is required") String country)
        implements Serializable {
    public static record OrderConfirmationDTO(String orderNumber, OrderStatus status) {}
}
