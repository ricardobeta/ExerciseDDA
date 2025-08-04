package com.rb.bp.accounts.application.port.out;


import com.rb.bp.accounts.domain.model.Account;

public interface AccountRepository {
    Account save(Account account);
    Account findById(Long accountId);
    Account updateAccount(Long accountId, Account account);
    void deleteById(Long accountId);
}
