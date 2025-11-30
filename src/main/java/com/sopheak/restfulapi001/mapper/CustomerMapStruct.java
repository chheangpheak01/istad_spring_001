package com.sopheak.restfulapi001.mapper;
import com.sopheak.restfulapi001.entities.Customer;
import com.sopheak.restfulapi001.model.dto.CustomerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring")
public interface CustomerMapStruct {
    @Mapping(source = "uuid",target = "uuid",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    CustomerResponseDto mapFromCustomerToCustomerResponseDto(Customer customer);
}
