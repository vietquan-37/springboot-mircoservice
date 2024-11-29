package com.vietquan37.ecommerce.service.impl;
import com.vietquan37.ecommerce.notification.NotificationProducer;
import com.vietquan37.ecommerce.notification.PaymentConfirmation;
import com.vietquan37.ecommerce.payload.request.PaymentDTO;
import com.vietquan37.ecommerce.repository.PaymentRepository;
import com.vietquan37.ecommerce.service.IPaymentService;
import com.vietquan37.ecommerce.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;
    @Override
    public Integer createPayment(PaymentDTO dto) {
        var payment=paymentRepository.save(paymentMapper.mapDTO(dto));
        // send to notification server
        notificationProducer.sendNotification(
                new PaymentConfirmation(
                        dto.orderReference(),
                        dto.amount(),
                        dto.paymentMethod(),
                        dto.customer().firstName(),
                        dto.customer().lastName(),
                        dto.customer().email()


                )
        );
        return payment.getId();
    }
}
