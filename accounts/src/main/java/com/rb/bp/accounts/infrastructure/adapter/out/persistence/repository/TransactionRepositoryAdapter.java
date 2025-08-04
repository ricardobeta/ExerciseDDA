package com.rb.bp.accounts.infrastructure.adapter.out.persistence.repository;

import com.rb.bp.accounts.application.port.out.TransactionRepository;
import com.rb.bp.accounts.domain.exception.TransactionException;
import com.rb.bp.accounts.domain.model.Report;
import com.rb.bp.accounts.domain.model.Transaction;
import com.rb.bp.accounts.infrastructure.adapter.out.persistence.entity.TransactionEntity;
import com.rb.bp.accounts.shared.mapper.TransactionMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryAdapter  implements TransactionRepository {
    private final TransactionJPARepository transactionJpaRepository;
    private final TransactionMapper transactionMapper;

    @Override
    @Transactional
    public Transaction save(Transaction transaction) {
        TransactionEntity entity = transactionMapper.toEntity(transaction);
        return transactionMapper.toDomain(transactionJpaRepository.save(entity));
    }

    @Override
    public Transaction findById(Long transactionId) {
        TransactionEntity entity = findTransactionEntityById(transactionId);
        return transactionMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public Transaction updateTransaction(Long transactionId, Transaction transaction) {
        TransactionEntity existing = findTransactionEntityById(transactionId);
        transactionMapper.updateEntityFromDomain(transaction, existing);
        return transactionMapper.toDomain(existing);
    }

    @Override
    @Transactional
    public void deleteById(Long transactionId) {
        TransactionEntity entity = findTransactionEntityById(transactionId);
        transactionJpaRepository.delete(entity);
    }

    @Override
    public List<Report> getReport(LocalDate startDate, LocalDate endDate, Long customerId) {
        List<TransactionEntity> transactions = transactionJpaRepository.getReportByDateRangeAndUser(startDate,endDate, customerId);
        return transactions.stream().map(transactionMapper::toReport).toList();
    }


    private TransactionEntity findTransactionEntityById(Long transactionId) {
        return transactionJpaRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionException("Transaction not found"));
    }}
