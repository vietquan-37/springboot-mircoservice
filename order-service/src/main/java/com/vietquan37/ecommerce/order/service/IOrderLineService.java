package com.vietquan37.ecommerce.order.service;

import com.vietquan37.ecommerce.order.payload.response.OrderLineResponse;

import java.util.List;

public interface IOrderLineService {
    List<OrderLineResponse> getAllOrderLines(Integer orderId);
}
