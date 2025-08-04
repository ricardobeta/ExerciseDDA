package com.rb.bp.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.rb.bp.customers.application",
		"com.rb.bp.customers.infrastructure",
		"com.rb.bp.customers.shared",
		"com.rb.bp.customers.domain"
})
public class CustomersApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomersApplication.class, args);
	}

}
