package com.vietquan37.ecommerce.mapper;

import com.vietquan37.ecommerce.entity.Customer;
import com.vietquan37.ecommerce.payload.request.CustomerDTO;
import com.vietquan37.ecommerce.payload.request.CustomerUpdateDTO;
import com.vietquan37.ecommerce.payload.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer mapDto(CustomerDTO dto);
    void updateDto(CustomerUpdateDTO dto, @MappingTarget Customer customer);
    CustomerResponse mapResponse(Customer customer);
}
