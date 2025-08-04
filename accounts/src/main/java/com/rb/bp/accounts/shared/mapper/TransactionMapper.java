package com.rb.bp.accounts.shared.mapper;

import com.rb.bp.accounts.domain.model.Report;
import com.rb.bp.accounts.domain.model.Transaction;
import com.rb.bp.accounts.infrastructure.adapter.out.persistence.entity.TransactionEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "account.accountId", source = "accountId")
    TransactionEntity toEntity(Transaction transaction);

    @Mapping(source = "account.accountId", target = "accountId")
    Transaction toDomain(TransactionEntity transactionEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(Transaction transaction, @MappingTarget TransactionEntity transactionEntity);

    @Mapping(source = "transactionDate", target = "date")
    @Mapping(source = "account.customerName", target = "customerName")
    @Mapping(source = "account.accountNumber", target = "accountNumber")
    @Mapping(source = "account.accountType", target = "accountType")
    @Mapping(source = "balanceBeforeTransaction", target = "initialBalance")
    @Mapping(source = "amount", target = "value")
    @Mapping(source = "balanceAfterTransaction", target = "availableBalance")
    Report toReport(TransactionEntity transactionEntity);

}
