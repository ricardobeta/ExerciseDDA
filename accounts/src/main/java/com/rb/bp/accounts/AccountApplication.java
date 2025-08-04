package com.rb.bp.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.rb.bp.accounts.application",
		"com.rb.bp.accounts.infrastructure",
		"com.rb.bp.accounts.shared",
		"com.rb.bp.accounts.domain"
})
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

}
