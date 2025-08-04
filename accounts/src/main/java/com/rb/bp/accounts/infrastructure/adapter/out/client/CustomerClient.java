package com.rb.bp.accounts.infrastructure.adapter.out.client;

import com.rb.bp.accounts.application.port.out.ValidateCustomerPort;
import com.rb.bp.accounts.domain.exception.AccountException;
import com.rb.bp.accounts.infrastructure.adapter.in.web.DTO.ErrorRs;
import com.rb.bp.accounts.infrastructure.adapter.out.client.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class CustomerClient implements ValidateCustomerPort {

    private final WebClient webClient;

    public CompletableFuture<CustomerDTO> findCustomerById(Long customerId) {
        return webClient
                .get()
                .uri("/customers/{id}", customerId)
                .retrieve()
                .onStatus(
                        status -> status.value() == 409 || status.value() == 404,
                        clientResponse -> clientResponse.bodyToMono(ErrorRs.class)
                                .flatMap(error -> Mono.error(new AccountException(error.getMessage())))
                )
                .bodyToMono(CustomerDTO.class)
                .toFuture();
    }

}
