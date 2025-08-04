package com.rb.bp.customers.shared.mapper;

import com.rb.bp.customers.domain.model.Customer;
import com.rb.bp.customers.infrastructure.adapter.out.persistence.entity.CustomerEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    @Mapping(source = "person.id", target = "id")
    @Mapping(source = "person.name", target = "name")
    @Mapping(source = "person.gender", target = "gender")
    @Mapping(source = "person.age", target = "age")
    @Mapping(source = "person.identification", target = "identification")
    @Mapping(source = "person.address", target = "address")
    @Mapping(source = "person.phone", target = "phone")
    @Mapping(source = "customerId", target = "customerId")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "status", target = "status")
    Customer toDomain(CustomerEntity customerEntity);


    @Mapping(target = "person.id", source = "id")
    @Mapping(target = "person.name", source = "name")
    @Mapping(target = "person.gender", source = "gender")
    @Mapping(target = "person.age", source = "age")
    @Mapping(target = "person.identification", source = "identification")
    @Mapping(target = "person.address", source = "address")
    @Mapping(target = "person.phone", source = "phone")
    @Mapping(target = "customerId", source = "customerId")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "status", source = "status")
    CustomerEntity toEntity(Customer domain);

    @Mapping(target = "person.id", source = "id")
    @Mapping(target = "person.name", source = "name")
    @Mapping(target = "person.gender", source = "gender")
    @Mapping(target = "person.age", source = "age")
    @Mapping(target = "person.identification", source = "identification")
    @Mapping(target = "person.address", source = "address")
    @Mapping(target = "person.phone", source = "phone")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(Customer domain, @MappingTarget CustomerEntity entity);
}
