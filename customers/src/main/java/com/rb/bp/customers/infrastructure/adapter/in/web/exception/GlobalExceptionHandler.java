package com.rb.bp.customers.infrastructure.adapter.in.web.exception;

import com.rb.bp.customers.domain.exception.CustomerException;
import com.rb.bp.customers.infrastructure.adapter.in.web.DTO.ErrorRs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorRs> handleCustomerException(CustomerException ex) {
        ErrorRs error = new ErrorRs();
        error.setMessage(ex.getMessage());
        if(ex.getMessage().contains("Customer not found.")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
