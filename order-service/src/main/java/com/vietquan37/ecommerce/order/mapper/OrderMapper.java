package com.vietquan37.ecommerce.order.mapper;

import com.vietquan37.ecommerce.order.entity.Order;
import com.vietquan37.ecommerce.order.payload.request.OrderDTO;
import com.vietquan37.ecommerce.order.payload.response.OrderResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "reference", source = "reference")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "method", source = "paymentMethod")
    @Mapping(target = "customerId", source = "customerId")
    Order mapDTO(OrderDTO orderDTO);

    OrderResponse mapResponse(Order order);
}
