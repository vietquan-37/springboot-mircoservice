package com.vietquan37.ecommerce.order.mapper;

import com.vietquan37.ecommerce.order.entity.OrderLine;
import com.vietquan37.ecommerce.order.payload.request.OrderLineDTO;
import com.vietquan37.ecommerce.order.payload.response.OrderLineResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderLineMapper {
    @Mapping(target = "order.id", source = "orderId")
    OrderLine mapDTO(OrderLineDTO orderLineDTO);
    OrderLineResponse mapResponse(OrderLine orderLine);
}
