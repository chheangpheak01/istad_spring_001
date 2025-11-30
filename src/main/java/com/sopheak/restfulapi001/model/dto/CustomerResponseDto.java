package com.sopheak.restfulapi001.model.dto;
import com.sopheak.restfulapi001.entities.Order;
import lombok.Builder;
import java.util.Set;

@Builder
public record CustomerResponseDto(
        String uuid,
        String customerName,
        String email,
        Boolean isDeleted
        //Set<Order> orders
) {}
