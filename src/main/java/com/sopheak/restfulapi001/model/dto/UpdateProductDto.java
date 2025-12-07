package com.sopheak.restfulapi001.model.dto;
import lombok.Builder;
import java.time.LocalDate;

@Builder
public record UpdateProductDto(
        String productName,
        LocalDate expireDate
){}
