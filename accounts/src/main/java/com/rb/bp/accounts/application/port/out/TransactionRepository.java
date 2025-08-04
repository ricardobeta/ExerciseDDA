package com.rb.bp.accounts.application.port.out;

import com.rb.bp.accounts.domain.model.Report;
import com.rb.bp.accounts.domain.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    Transaction findById(Long transactionId);
    Transaction updateTransaction(Long transactionId, Transaction transaction);
    void deleteById(Long transactionId);
    List<Report> getReport(LocalDate startDate,LocalDate endDate, Long customerId);
}
