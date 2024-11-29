package com.vietquan37.ecommerce.service;

import com.vietquan37.ecommerce.payload.request.PaymentDTO;

public interface IPaymentService {
    Integer createPayment(PaymentDTO dto);
}
