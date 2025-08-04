package com.rb.bp.accounts.application.service;

import com.rb.bp.accounts.application.port.in.CRUDTransactionUseCase;
import com.rb.bp.accounts.application.port.out.TransactionRepository;
import com.rb.bp.accounts.domain.exception.TransactionException;
import com.rb.bp.accounts.domain.model.Account;
import com.rb.bp.accounts.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class TransactionService implements CRUDTransactionUseCase {


    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    // Por motivos de pruebas la fecha se va enviar desde el body de creación
    @Override
    public Transaction createTransaction(Transaction transaction) {
        transaction.validatedRequiredFields();
        Account account = accountService.findById(transaction.getAccountId());
        transaction.setBalanceBeforeTransaction(account.getInitialBalance());
        BigDecimal newBalance = account.getInitialBalance().add(transaction.getAmount());
        if(newBalance.compareTo(BigDecimal.ZERO) < 0) {
          throw  new TransactionException("Balance not available");
        }
        transaction.setBalanceAfterTransaction(newBalance);
        //transaction.setTransactionDate(LocalDate.now());
        Transaction transactionRs = transactionRepository.save(transaction);
        account.setInitialBalance(transactionRs.getBalanceAfterTransaction());
        accountService.updateAccount(account.getAccountId(),account);
        return transactionRs;
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId);
    }

    //INFO una transaction deberia tener cuidado al actualizar.
    @Override
    public Transaction updateTransaction(Long transactionId, Transaction updatedTransaction) {
        return transactionRepository.updateTransaction(transactionId, updatedTransaction);
    }

    //INFO una transaction no deberia ser eliminada.
    @Override
    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}
