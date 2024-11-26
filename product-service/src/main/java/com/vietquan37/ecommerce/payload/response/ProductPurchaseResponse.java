package com.vietquan37.ecommerce.payload.response;

import java.math.BigDecimal;

public record ProductPurchaseResponse (
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
){
}
