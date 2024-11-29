package com.vietquan37.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PurchaseDTO(
        @NotNull
        Integer productId,
        @Positive
        double quantity

) {
}
