package com.vietquan37.ecommerce.payload.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseDTO(
        @NotNull
        Integer productId,
        @Positive
        @NotNull
        double quantity
) {
}
