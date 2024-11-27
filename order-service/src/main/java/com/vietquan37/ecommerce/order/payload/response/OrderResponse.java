package com.vietquan37.ecommerce.order.payload.response;

import com.vietquan37.ecommerce.order.enumClass.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod method,
        String customerId
        ) {
}
