package com.vietquan37.ecommerce.repository;

import com.vietquan37.ecommerce.entity.Customer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {

}
