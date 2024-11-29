package com.vietquan37.ecommerce.mapper;

import com.vietquan37.ecommerce.entity.Payment;
import com.vietquan37.ecommerce.payload.request.PaymentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(target = "orderId",source = "orderId")
    @Mapping(target = "paymentMethod",source = "paymentMethod")
    @Mapping(target = "amount",source = "amount")
    Payment mapDTO(PaymentDTO paymentDTO);

}
