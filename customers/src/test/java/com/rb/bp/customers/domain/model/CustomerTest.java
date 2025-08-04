package com.rb.bp.customers.domain.model;

import com.rb.bp.customers.domain.exception.CustomerException;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class CustomerTest {
    @Test
    void customerIsActive_shouldPassWhenStatusIsTrue() {
        Customer customer = new Customer();
        customer.setStatus(true);
        assertDoesNotThrow(customer::customerIsActive);
    }

    @Test
    void customerIsActive_shouldThrowExceptionWhenStatusIsFalse() {
        Customer customer = new Customer();
        customer.setStatus(false);

        CustomerException exception = assertThrows(CustomerException.class, customer::customerIsActive);
        assertEquals("Customer not Active.", exception.getMessage());
    }

    @Test
    void validatedRequired_shouldPassWhenAllFieldsArePresent() {
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setPassword("123456");
        customer.setIdentification("1234567890");
        customer.setName("Juan");

        assertDoesNotThrow(customer::validatedRequired);
    }


    @Test
    void validatedRequired_shouldThrowExceptionWhenPasswordIsBlank() {
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setPassword("   ");
        customer.setIdentification("1234567890");
        customer.setName("Juan");

        CustomerException exception = assertThrows(CustomerException.class, customer::validatedRequired);
        assertEquals("Password is required.", exception.getMessage());
    }

    @Test
    void validatedRequired_shouldThrowExceptionWhenIdentificationIsBlank() {
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setPassword("123456");
        customer.setIdentification("   ");
        customer.setName("Juan");

        CustomerException exception = assertThrows(CustomerException.class, customer::validatedRequired);
        assertEquals("Identification is required.", exception.getMessage());
    }

    @Test
    void validatedRequired_shouldThrowExceptionWhenNameIsNull() {
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setPassword("123456");
        customer.setIdentification("1234567890");

        CustomerException exception = assertThrows(CustomerException.class, customer::validatedRequired);
        assertEquals("Name is required.", exception.getMessage());
    }
}