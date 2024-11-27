package com.vietquan37.ecommerce.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {
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
