package com.vietquan37.ecommerce.order.service.impl;

import com.vietquan37.ecommerce.order.entity.OrderLine;
import com.vietquan37.ecommerce.order.mapper.OrderLineMapper;
import com.vietquan37.ecommerce.order.repository.OrderLineRepository;
import com.vietquan37.ecommerce.order.payload.request.OrderLineDTO;
import com.vietquan37.ecommerce.order.payload.response.OrderLineResponse;
import com.vietquan37.ecommerce.order.service.IOrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService implements IOrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public void createOrderLine(OrderLineDTO dto) {
        orderLineRepository.save(orderLineMapper.mapDTO(dto));

    }
    public List<OrderLineResponse> getAllOrderLines(Integer orderId) {
        List<OrderLine> orderLines = orderLineRepository.findAllByOrderId(orderId);
        return orderLines.stream().map(orderLineMapper::mapResponse).toList();
    }

}
