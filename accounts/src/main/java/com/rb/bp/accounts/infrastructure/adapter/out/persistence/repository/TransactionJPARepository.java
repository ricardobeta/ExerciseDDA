package com.rb.bp.accounts.infrastructure.adapter.out.persistence.repository;

import com.rb.bp.accounts.infrastructure.adapter.out.persistence.entity.AccountEntity;
import com.rb.bp.accounts.infrastructure.adapter.out.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionJPARepository extends JpaRepository<TransactionEntity, Long> {

    @Query("""
    SELECT t FROM TransactionEntity t
    JOIN t.account a
    WHERE t.transactionDate BETWEEN :startDate AND :endDate
      AND a.customerId = :customerId
       ORDER BY a.accountNumber
""")
    List<TransactionEntity> getReportByDateRangeAndUser(@Param("startDate") LocalDate startDate,
                                                        @Param("endDate") LocalDate endDate,
                                                        @Param("customerId") Long customerId);;
}
