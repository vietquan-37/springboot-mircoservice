package com.vietquan37.ecommerce.payload.request;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductDTO(
        @NotNull
        String name,
        @NotNull
        String description,
        @Positive
        double stock,
        @Positive
        BigDecimal price,
        @NotNull
        Integer categoryId
        ) {
}
