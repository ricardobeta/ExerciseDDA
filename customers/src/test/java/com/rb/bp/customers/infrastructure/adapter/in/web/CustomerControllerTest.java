package com.rb.bp.customers.infrastructure.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rb.bp.customers.application.port.out.CustomerRepository;
import com.rb.bp.customers.domain.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Customer savedCustomer;

    @BeforeEach
    void setUp() {
        savedCustomer = new Customer();
        savedCustomer.setName("Test Integration");
        savedCustomer.setIdentification("4434588890");
        savedCustomer.setPassword("password");
        savedCustomer.setAddress("Direccion");
        savedCustomer.setPhone("12345222");
        savedCustomer.setAge(31);
        savedCustomer.setGender("M");
        savedCustomer.setStatus(true);
        savedCustomer =customerRepository.save(savedCustomer);
    }


    @Test
    void createCustomer_shouldPersistAndReturnCustomer() throws Exception {
        savedCustomer.setIdentification("3123243");
        String responseBody = mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(savedCustomer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerId").exists())
                .andReturn()
                .getResponse()
                .getContentAsString();


        Long customerId = objectMapper.readTree(responseBody).get("customerId").asLong();
        customerRepository.deleteById(customerId);

    }

    @Test
    void getCustomerById_shouldReturnCustomerById() throws Exception {
        mockMvc.perform(get("/api/customers/" + savedCustomer.getCustomerId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Integration"));
        customerRepository.deleteById(savedCustomer.getCustomerId());
    }



    @Test
    void updateCustomer_shouldModifyCustomerData() throws Exception {
        Customer update = new Customer();
        update.setPhone("02202020");
        mockMvc.perform(patch("/api/customers/" + savedCustomer.getCustomerId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phone").value("02202020"));
        customerRepository.deleteById(savedCustomer.getCustomerId());
    }

    @Test
    void deleteCustomer_shouldRemoveCustomer() throws Exception {
        mockMvc.perform(delete("/api/customers/" + savedCustomer.getCustomerId()))
                .andExpect(status().isNoContent());
    }

}