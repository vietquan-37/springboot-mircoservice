package com.vietquan37.ecommerce.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        @NotNull
        String id,
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotNull
        @Email
        String email
) {
}
