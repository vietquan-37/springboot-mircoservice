package com.vietquan37.ecommerce.orderline;

import java.math.BigDecimal;

public record OrderLineDTO(
        Integer productId,
        double quantity,
        Integer orderId,
        BigDecimal price


) {
}
