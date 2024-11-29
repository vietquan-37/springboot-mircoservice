package com.vietquan37.ecommerce.order.repository;

import com.vietquan37.ecommerce.order.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllByOrderId(int orderId);
}
