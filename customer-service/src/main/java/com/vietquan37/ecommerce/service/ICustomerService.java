package com.vietquan37.ecommerce.service;

import com.vietquan37.ecommerce.payload.request.CustomerDTO;
import com.vietquan37.ecommerce.payload.request.CustomerUpdateDTO;
import com.vietquan37.ecommerce.payload.response.CustomerResponse;
import com.vietquan37.ecommerce.payload.response.PageResponse;
import org.springframework.data.domain.Page;


public interface ICustomerService {
    String createCustomer(CustomerDTO dto);
    void updateCustomer(String id,CustomerUpdateDTO dto);
    PageResponse<CustomerResponse> findAllCustomers(Integer page, Integer size);
    CustomerResponse findCustomerById(String id);
}
