package com.sopheak.restfulapi001.model.dto;
import lombok.Builder;
import java.time.LocalDate;
import java.util.Set;

@Builder
public record CreateProductDto(
        String productName,
        LocalDate expireDate,
        Set<String> categoriesName
) {}
