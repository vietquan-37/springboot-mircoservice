package com.vietquan37.ecommerce.order.payload.response;

import java.math.BigDecimal;

public record OrderLineResponse(
        Integer id,
        double quantity,
        BigDecimal price

) {
}
