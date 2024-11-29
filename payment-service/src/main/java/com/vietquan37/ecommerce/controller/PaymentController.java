package com.vietquan37.ecommerce.controller;

import com.vietquan37.ecommerce.payload.request.PaymentDTO;
import com.vietquan37.ecommerce.payload.response.APIResponse;
import com.vietquan37.ecommerce.service.IPaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final IPaymentService paymentService;

    @PostMapping
    public ResponseEntity<Integer> createPayment(@RequestBody @Valid PaymentDTO payment) {
        var resp=paymentService.createPayment(payment);
        return ResponseEntity.ok(resp);
    }


}
