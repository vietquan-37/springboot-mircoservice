package com.vietquan37.ecommerce.order.payload.request;
import com.vietquan37.ecommerce.order.enumClass.PaymentMethod;
import com.vietquan37.ecommerce.product.PurchaseDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


import java.math.BigDecimal;
import java.util.List;

public record OrderDTO(
   String reference,
   @Positive
   BigDecimal amount,
   @NotNull
   PaymentMethod paymentMethod,
   @NotNull
   String customerId,
   @NotEmpty
   List<PurchaseDTO> products
        ) {
}
