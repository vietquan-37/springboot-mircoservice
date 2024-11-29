package com.vietquan37.ecommerce.payment;

import com.vietquan37.ecommerce.customer.CustomerResponse;
import com.vietquan37.ecommerce.order.enumClass.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
