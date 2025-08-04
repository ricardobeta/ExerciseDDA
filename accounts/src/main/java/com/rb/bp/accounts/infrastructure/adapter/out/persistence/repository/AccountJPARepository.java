package com.rb.bp.accounts.infrastructure.adapter.out.persistence.repository;

import com.rb.bp.accounts.infrastructure.adapter.out.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJPARepository extends JpaRepository<AccountEntity, Long> {
}
