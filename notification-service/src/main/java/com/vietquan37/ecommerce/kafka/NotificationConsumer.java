package com.vietquan37.ecommerce.kafka;

import com.vietquan37.ecommerce.email.EmailService;
import com.vietquan37.ecommerce.entity.Notification;
import com.vietquan37.ecommerce.enumClass.NotificationType;
import com.vietquan37.ecommerce.kafka.order.OrderConfirmation;
import com.vietquan37.ecommerce.kafka.payment.PaymentConfirmation;
import com.vietquan37.ecommerce.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic", groupId = "notificationGroup")
    public void consumePaymentSuccessfullyNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming message from payment-topic :: {}", paymentConfirmation);

        notificationRepository.save(Notification.builder()
                .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                .notificationTime(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build());

        String customerName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic", groupId = "notificationGroup")
    public void consumeOrderNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(format("Consuming the message from order-topic Topic:: %s", orderConfirmation));

        notificationRepository.save(Notification.builder()
                .notificationType(NotificationType.ORDER_CONFIRMATION)
                .notificationTime(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build());

        String customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();
        emailService.sendOrderConfirmationSuccessEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
}
