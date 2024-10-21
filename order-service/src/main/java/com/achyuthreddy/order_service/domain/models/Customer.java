package com.achyuthreddy.order_service.domain.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Customer(
        @NotBlank(message = "Customer name is required") String name,
        @NotBlank(message = "Customer email is required") Email email,
        @NotBlank(message = "Customer phone no is required") String phone) {}