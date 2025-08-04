package com.rb.bp.customers.infrastructure.adapter.out.persistence.repository;

import com.rb.bp.customers.application.port.out.CustomerRepository;
import com.rb.bp.customers.domain.exception.CustomerException;
import com.rb.bp.customers.domain.model.Customer;
import com.rb.bp.customers.infrastructure.adapter.out.persistence.entity.CustomerEntity;
import com.rb.bp.customers.shared.mapper.CustomerMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final CustomerJPARepository customerJPARepository;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = customerMapper.toEntity(customer);
        try {
            return customerMapper.toDomain(customerJPARepository.save(customerEntity));
        } catch (DataIntegrityViolationException e) {
            if (e.getCause().getMessage().contains("person_identification_key")) {
                throw new CustomerException("Identification already registered.");
            }
            throw new CustomerException("Error saving client.");
        } catch (Exception e) {
            throw new CustomerException("Unexpected error while saving the client.");
        }
    }

    @Override
    public Customer findById(Long customerId) {
        CustomerEntity searchCustomer= searchCustomerEntity(customerId);
        return customerMapper.toDomain(searchCustomer);
    }

    @Override
    @Transactional
    public Customer updatedCustomer(Long customerId, Customer customer) {
        CustomerEntity searchCustomer= searchCustomerEntity(customerId);
        customerMapper.updateEntityFromDomain(customer, searchCustomer);
        return customerMapper.toDomain(searchCustomer);
    }

    @Override
    @Transactional
    public void deleteById(Long customerId) {
        CustomerEntity searchCustomer= searchCustomerEntity(customerId);
        customerJPARepository.delete(searchCustomer);
    }

    private CustomerEntity searchCustomerEntity(Long customerId){
        Optional<CustomerEntity> optCustomer = customerJPARepository.findById(customerId);
        if(optCustomer.isEmpty()){
            throw  new CustomerException("Customer not found.");
        }
        return optCustomer.get();
    }
}
