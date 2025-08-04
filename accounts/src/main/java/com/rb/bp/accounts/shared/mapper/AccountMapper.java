package com.rb.bp.accounts.shared.mapper;

import com.rb.bp.accounts.domain.model.Account;
import com.rb.bp.accounts.infrastructure.adapter.out.persistence.entity.AccountEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountMapper {


    @Mapping(target = "transactions", ignore = true)
    AccountEntity toEntity(Account account);

    Account toDomain(AccountEntity accountEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(Account account, @MappingTarget AccountEntity accountEntity);
}
