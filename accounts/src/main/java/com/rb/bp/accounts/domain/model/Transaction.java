package com.rb.bp.accounts.domain.model;

import com.rb.bp.accounts.domain.exception.AccountException;
import com.rb.bp.accounts.domain.exception.TransactionException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.EnumSet;

@Getter
@Setter
@ToString
public class Transaction {
    private Long transactionId;
    private Long accountId;
    private LocalDate transactionDate;
    private TransactionType transactionType;
    private BigDecimal amount;
    private BigDecimal balanceAfterTransaction;
    private BigDecimal balanceBeforeTransaction;

    public void validatedRequiredFields(){
        if(accountId == null){
            throw new TransactionException("Account Id is required.");
        }
        if(amount == null){
            throw new TransactionException("Amount is required.");
        }
        if(transactionDate == null){
            throw new TransactionException("transactionDate is required.");
        }
        validateAccountType();
    }

    private void validateAccountType() {
        if (transactionType == null) {
            throw new TransactionException("Transaction type is required.");
        }
        if (!EnumSet.of(TransactionType.DEPOSIT, TransactionType.WITHDRAWAL).contains(transactionType)) {
            throw new AccountException("Invalid transaction type: must be DEPOSIT or WITHDRAWAL.");
        }
    }
}
