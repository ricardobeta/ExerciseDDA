package com.rb.bp.accounts.infrastructure.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.rb.bp.accounts.infrastructure.adapter.out.persistence.entity")
@EnableJpaRepositories(basePackages = "com.rb.bp.accounts.infrastructure.adapter.out.persistence.repository")
public class PersistenceConfig {
}
