package com.rb.bp.customers.application.port.out;

import com.rb.bp.customers.domain.model.Customer;

import java.util.Optional;


public interface CustomerRepository {
    Customer save(Customer customer);
    Customer findById(Long customerId);
    Customer updatedCustomer(Long customerId,Customer customer);
    void deleteById(Long customerId);
}
