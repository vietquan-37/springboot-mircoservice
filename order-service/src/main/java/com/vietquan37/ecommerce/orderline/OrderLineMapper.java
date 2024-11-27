package com.vietquan37.ecommerce.orderline;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderLineMapper {
    @Mapping(target = "order.id", source = "orderId")
    OrderLine mapDTO(OrderLineDTO orderLineDTO);

    OrderLineResponse mapResponse(OrderLine orderLine);
}
