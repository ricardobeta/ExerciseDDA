package com.rb.bp.customers.application.port.in;

import com.rb.bp.customers.domain.model.Customer;

import java.util.Optional;

public interface CRUDCustomerUseCase {
    Customer createCustomer(Customer customer);
    Customer findById(Long customerId);
    Customer updatedCustomer(Long customerId,Customer customer);
    void deleteCustomer(Long customerId);
}
