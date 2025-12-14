package com.sopheak.restfulapi001.model.dto;
import lombok.Builder;
import java.util.Date;
import java.util.Set;

@Builder
public record OrderResponseDto(
        String uuid,
        String orderName,
        Date orderedDate,
        Boolean isDeleted,
        String location,
        String status,
        Set<ProductResponseDto> products,
        CustomerResponseDto customer
){}
