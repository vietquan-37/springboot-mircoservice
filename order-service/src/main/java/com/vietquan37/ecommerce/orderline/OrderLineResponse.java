package com.vietquan37.ecommerce.orderline;

import java.math.BigDecimal;

public record OrderLineResponse(
        Integer id,
        double quantity,
        BigDecimal price

) {
}
