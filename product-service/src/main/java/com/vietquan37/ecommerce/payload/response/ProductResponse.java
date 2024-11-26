package com.vietquan37.ecommerce.payload.response;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        double stock,
        BigDecimal price,
        Integer categoryId,
        String categoryName

) {
}
