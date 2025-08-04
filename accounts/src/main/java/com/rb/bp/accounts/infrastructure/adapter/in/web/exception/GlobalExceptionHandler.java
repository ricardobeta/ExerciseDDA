package com.rb.bp.accounts.infrastructure.adapter.in.web.exception;

import com.rb.bp.accounts.domain.exception.AccountException;
import com.rb.bp.accounts.domain.exception.TransactionException;
import com.rb.bp.accounts.infrastructure.adapter.in.web.DTO.ErrorRs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorRs> handleCustomerException(AccountException ex) {
        ErrorRs error = new ErrorRs();
        error.setMessage(ex.getMessage());
        if(ex.getMessage().contains("Account not found")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<ErrorRs> handleTransactionException(TransactionException ex) {
        ErrorRs error = new ErrorRs();
        error.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
