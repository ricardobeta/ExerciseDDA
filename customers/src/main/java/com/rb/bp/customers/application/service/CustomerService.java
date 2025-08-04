package com.rb.bp.customers.application.service;

import com.rb.bp.customers.application.port.in.CRUDCustomerUseCase;
import com.rb.bp.customers.application.port.out.CustomerRepository;
import com.rb.bp.customers.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements CRUDCustomerUseCase {


    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        customer.validatedRequired();
        customer.setStatus(Boolean.TRUE);
        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long customerId) {
        Customer customer= customerRepository.findById(customerId);
        customer.customerIsActive();
        return customer;
    }

    @Override
    public Customer updatedCustomer(Long customerId, Customer customer) {
        return customerRepository.updatedCustomer(customerId,customer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
