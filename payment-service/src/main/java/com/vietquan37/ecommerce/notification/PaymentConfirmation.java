package com.vietquan37.ecommerce.notification;

import com.vietquan37.ecommerce.enumClass.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod method,
        String customerFirstName,
        String customerLastName,
        String customerEmail

) {
}
