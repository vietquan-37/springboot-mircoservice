package com.vietquan37.ecommerce.service.impl;

import com.vietquan37.ecommerce.entity.Customer;
import com.vietquan37.ecommerce.exception.NotFoundException;
import com.vietquan37.ecommerce.mapper.CustomerMapper;
import com.vietquan37.ecommerce.payload.request.CustomerDTO;
import com.vietquan37.ecommerce.payload.request.CustomerUpdateDTO;
import com.vietquan37.ecommerce.payload.response.CustomerResponse;
import com.vietquan37.ecommerce.payload.response.PageResponse;
import com.vietquan37.ecommerce.repository.CustomerRepository;
import com.vietquan37.ecommerce.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    public String createCustomer(CustomerDTO dto) {
        var customer= customerRepository.save(customerMapper.mapDto(dto));
        return customer.getId();
    }

    @Override
    public void updateCustomer(String id, CustomerUpdateDTO dto) {
        var customer=customerRepository.findById(id).orElseThrow(()->new NotFoundException("Customer not found"));
        customerMapper.updateDto(dto,customer);
        customerRepository.save(customer);

    }

    @Override
    public PageResponse<CustomerResponse> findAllCustomers(Integer page, Integer size) {
        if (page <= 0 || size <= 0) {
            throw new IllegalArgumentException("Page and pageSize must be non-negative and pageSize must be greater than zero");
        }
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Customer> customers = customerRepository.findAll(pageable);
        return PageResponse.<CustomerResponse>builder()
                .currentPage(page)
                .pageSize(customers.getSize())
                .totalElements(customers.getTotalElements())
                .totalPages(customers.getTotalPages())
                .data(customers.map(customerMapper::mapResponse).stream().toList())
                .build();
    }

    @Override
    public CustomerResponse findCustomerById(String id) {
        Customer customer=customerRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Customer not found"));
        return customerMapper.mapResponse(customer);
    }
}
