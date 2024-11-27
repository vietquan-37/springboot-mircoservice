package com.vietquan37.ecommerce.order.kafka;

import com.vietquan37.ecommerce.customer.CustomerResponse;
import com.vietquan37.ecommerce.order.enumClass.PaymentMethod;
import com.vietquan37.ecommerce.product.ProductPurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalPrice,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<ProductPurchaseResponse>products
) {
}
