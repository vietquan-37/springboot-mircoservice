package com.vietquan37.ecommerce.entity;

import com.vietquan37.ecommerce.enumClass.NotificationType;
import com.vietquan37.ecommerce.kafka.order.OrderConfirmation;
import com.vietquan37.ecommerce.kafka.payment.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {
    @Id
    private String id;
    private NotificationType notificationType;
    private LocalDateTime notificationTime;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
