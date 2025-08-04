package com.rb.bp.accounts.application.service;

import com.rb.bp.accounts.application.port.in.CRUDAccountUseCase;
import com.rb.bp.accounts.application.port.out.AccountRepository;
import com.rb.bp.accounts.application.port.out.ValidateCustomerPort;
import com.rb.bp.accounts.domain.model.Account;
import com.rb.bp.accounts.infrastructure.adapter.out.client.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService implements CRUDAccountUseCase {


    private final AccountRepository accountRepository;
    private final ValidateCustomerPort validateCustomerPort;

    @Override
    public Account createAccount(Account account) {
        account.setStatus(true);
        account.validateRequiredFields();
        CustomerDTO customerDTO = validateCustomerPort.findCustomerById(account.getCustomerId()).join();
        account.setCustomerName(customerDTO.getName());
        return accountRepository.save(account);
    }

    @Override
    public Account findById(Long accountId) {
        Account account = accountRepository.findById(accountId);
        account.isAccountActive();
        return account;
    }

    @Override
    public Account updateAccount(Long accountId, Account account) {
        return accountRepository.updateAccount(accountId, account);
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }
}
