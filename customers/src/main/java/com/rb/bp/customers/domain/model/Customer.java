package com.rb.bp.customers.domain.model;

import com.rb.bp.customers.domain.exception.CustomerException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Customer extends Person {
    private Long customerId;
    private String password;
    private Boolean status;

    public void customerIsActive(){
        if(Boolean.FALSE.equals(this.status)){
            throw new CustomerException("Customer not Active.");
        }
    }

    public void validatedRequired() {
        if (password == null || password.isBlank()) {
            throw new CustomerException("Password is required.");
        }
        if (getIdentification() == null || getIdentification().isBlank()) {
            throw new CustomerException("Identification is required.");
        }
        if (getName() == null || getName().isBlank()) {
            throw new CustomerException("Name is required.");
        }
    }
}
