package com.rb.bp.accounts.application.port.in;

import com.rb.bp.accounts.domain.model.Account;

public interface CRUDAccountUseCase {
    Account createAccount(Account account);
    Account findById(Long accountId);
    Account updateAccount(Long accountId, Account account);
    void deleteAccount(Long accountId);
}
