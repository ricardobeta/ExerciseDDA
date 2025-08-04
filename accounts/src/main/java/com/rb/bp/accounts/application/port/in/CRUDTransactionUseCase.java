package com.rb.bp.accounts.application.port.in;

import com.rb.bp.accounts.domain.model.Transaction;

public interface CRUDTransactionUseCase {
    Transaction createTransaction(Transaction transaction);

    Transaction getTransactionById(Long transactionId);

    Transaction updateTransaction(Long transactionId, Transaction updatedTransaction);

    void deleteTransaction(Long transactionId);
}
