package com.vietquan37.ecommerce.repository;

import com.vietquan37.ecommerce.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
