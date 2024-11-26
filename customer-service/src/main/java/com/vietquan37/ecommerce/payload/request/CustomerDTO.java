package com.vietquan37.ecommerce.payload.request;

import com.vietquan37.ecommerce.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerDTO(

        @NotNull(message = "Customer firstname is required")
        String firstName,
        @NotNull(message = "Customer lastname is required")
        String lastName,
        @Email
        String email,
        Address address
) {
}