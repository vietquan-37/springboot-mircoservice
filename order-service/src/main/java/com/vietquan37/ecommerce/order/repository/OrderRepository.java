package com.vietquan37.ecommerce.order.repository;

import com.vietquan37.ecommerce.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
