package com.vietquan37.ecommerce.payload.request;

import com.vietquan37.ecommerce.enumClass.PaymentMethod;

import java.math.BigDecimal;

public record PaymentDTO(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer

) {
}
