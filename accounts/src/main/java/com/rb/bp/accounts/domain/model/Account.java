package com.rb.bp.accounts.domain.model;

import com.rb.bp.accounts.domain.exception.AccountException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.EnumSet;

@Getter
@Setter
@ToString
public class Account {
    private Long accountId;
    private String accountNumber;
    private AccountType accountType;
    private BigDecimal initialBalance;
    private Boolean status;
    private String customerName;
    private Long customerId;

    public void validateRequiredFields() {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new AccountException("Account number is required.");
        }
        if (initialBalance == null) {
            throw new AccountException("Initial balance is required.");
        }
        if (status == null) {
            throw new AccountException("Status is required.");
        }
        validateAccountType();
    }

    public void isAccountActive() {
        if (Boolean.FALSE.equals(this.status)) {
            throw new AccountException("Account is not active.");
        }
    }

    private void validateAccountType() {
        if (accountType == null) {
            throw new AccountException("Account type is required.");
        }
        if (!EnumSet.of(AccountType.SAVING, AccountType.CHECKING).contains(accountType)) {
            throw new AccountException("Invalid account type: must be SAVINGS or CHECKING.");
        }
    }
}
