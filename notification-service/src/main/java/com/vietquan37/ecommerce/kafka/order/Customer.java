package com.vietquan37.ecommerce.kafka.order;

public record Customer(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
