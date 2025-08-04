package com.rb.bp.accounts.infrastructure.adapter.out.persistence.repository;

import com.rb.bp.accounts.application.port.out.AccountRepository;
import com.rb.bp.accounts.domain.exception.AccountException;
import com.rb.bp.accounts.domain.model.Account;
import com.rb.bp.accounts.infrastructure.adapter.out.persistence.entity.AccountEntity;
import com.rb.bp.accounts.shared.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepository {

    private final AccountJPARepository accountJPARepository;
    private final AccountMapper accountMapper;

    @Override
    public Account save(Account account) {
        AccountEntity entity = accountMapper.toEntity(account);
        try {
            return accountMapper.toDomain(accountJPARepository.save(entity));
        } catch (DataIntegrityViolationException e) {
            if (e.getCause().getMessage().contains("account_account_number_key")) {
                throw new AccountException("Account number already registered.");
            }
            throw new AccountException("Error saving Account.");
        } catch (Exception e) {
            throw new AccountException("Unexpected error while saving the account.");
        }

    }

    @Override
    public Account findById(Long accountId) {
        return accountMapper.toDomain(findEntity(accountId));
    }

    @Override
    public Account updateAccount(Long accountId, Account account) {
        AccountEntity entity = findEntity(accountId);
        accountMapper.updateEntityFromDomain(account, entity);
        return accountMapper.toDomain(accountJPARepository.save(entity));
    }

    @Override
    public void deleteById(Long accountId) {
        AccountEntity entity = findEntity(accountId);
        accountJPARepository.delete(entity);
    }

    private AccountEntity findEntity(Long accountId) {
        return accountJPARepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Account not found"));
    }
}
