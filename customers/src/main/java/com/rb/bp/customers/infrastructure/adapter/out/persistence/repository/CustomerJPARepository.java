package com.rb.bp.customers.infrastructure.adapter.out.persistence.repository;

import com.rb.bp.customers.infrastructure.adapter.out.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJPARepository extends JpaRepository<CustomerEntity, Long> {
}
