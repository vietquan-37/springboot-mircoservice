package com.vietquan37.ecommerce.order.payload.request;


import java.math.BigDecimal;

public record OrderLineDTO(
        Integer productId,
        double quantity,
        Integer orderId


) {
}
