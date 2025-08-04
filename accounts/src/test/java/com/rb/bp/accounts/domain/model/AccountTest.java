package com.rb.bp.accounts.domain.model;

import com.rb.bp.accounts.domain.exception.AccountException;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ActiveProfiles("test")
class AccountTest {
    @Test
    void validateRequiredFields_shouldPassWhenAllFieldsArePresent() {
        Account account = new Account();
        account.setAccountId(1L);
        account.setAccountNumber("1234567890");
        account.setAccountType(AccountType.SAVING);
        account.setInitialBalance(BigDecimal.valueOf(1000));
        account.setStatus(true);

        assertThatCode(account::validateRequiredFields).doesNotThrowAnyException();
    }

    @Test
    void validateRequiredFields_shouldFailWhenAccountNumberIsNull() {
        Account account = new Account();
        account.setAccountId(1L);
        account.setAccountNumber(null);
        account.setAccountType(AccountType.CHECKING);
        account.setInitialBalance(BigDecimal.valueOf(500));
        account.setStatus(true);

        assertThatThrownBy(account::validateRequiredFields)
                .isInstanceOf(AccountException.class)
                .hasMessage("Account number is required.");
    }

    @Test
    void validateRequiredFields_shouldFailWhenAccountTypeIsBlank() {
        Account account = new Account();
        account.setAccountId(1L);
        account.setAccountNumber("1234567890");
        account.setInitialBalance(BigDecimal.valueOf(200));
        account.setStatus(true);

        assertThatThrownBy(account::validateRequiredFields)
                .isInstanceOf(AccountException.class)
                .hasMessage("Account type is required.");
    }

    @Test
    void validateRequiredFields_shouldFailWhenInitialBalanceIsNull() {
        Account account = new Account();
        account.setAccountId(1L);
        account.setAccountNumber("1234567890");
        account.setAccountType(AccountType.SAVING);
        account.setInitialBalance(null);
        account.setStatus(true);

        assertThatThrownBy(account::validateRequiredFields)
                .isInstanceOf(AccountException.class)
                .hasMessage("Initial balance is required.");
    }

    @Test
    void validateRequiredFields_shouldFailWhenStatusIsNull() {
        Account account = new Account();
        account.setAccountId(1L);
        account.setAccountNumber("1234567890");
        account.setAccountType(AccountType.SAVING);
        account.setInitialBalance(BigDecimal.valueOf(1500));
        account.setStatus(null);

        assertThatThrownBy(account::validateRequiredFields)
                .isInstanceOf(AccountException.class)
                .hasMessage("Status is required.");
    }

    @Test
    void isAccountIsActive_shouldPassWhenStatusTrue() {
        Account account = new Account();
        account.setAccountId(1L);
        account.setAccountNumber("1234567890");
        account.setAccountType(AccountType.SAVING);
        account.setInitialBalance(BigDecimal.valueOf(1000));
        account.setStatus(true);

        assertThatCode(account::isAccountActive).doesNotThrowAnyException();
    }

    @Test
    void isAccountIsActive_shouldFailWhenStatusFalse() {
        Account account = new Account();
        account.setAccountId(1L);
        account.setAccountNumber("1234567890");
        account.setAccountType(AccountType.SAVING);
        account.setInitialBalance(BigDecimal.valueOf(1000));
        account.setStatus(false);

        assertThatThrownBy(account::isAccountActive)
                .isInstanceOf(AccountException.class)
                .hasMessage("Account is not active.");
    }

}