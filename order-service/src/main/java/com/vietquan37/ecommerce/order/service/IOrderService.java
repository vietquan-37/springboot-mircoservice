package com.vietquan37.ecommerce.order.service;

import com.vietquan37.ecommerce.order.payload.request.OrderDTO;
import com.vietquan37.ecommerce.order.payload.response.OrderResponse;
import com.vietquan37.ecommerce.order.payload.response.PageResponse;

public interface IOrderService {
    Integer createOrder(OrderDTO dto);
    PageResponse<OrderResponse> findAllOrder(int page, int size);
    OrderResponse findOrderById(Integer id);
}
