package com.rb.bp.accounts.application.port.out;

import com.rb.bp.accounts.infrastructure.adapter.out.client.dto.CustomerDTO;

import java.util.concurrent.CompletableFuture;

public interface ValidateCustomerPort {
    CompletableFuture<CustomerDTO> findCustomerById(Long customerId);
}
