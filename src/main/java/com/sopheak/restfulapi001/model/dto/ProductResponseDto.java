package com.sopheak.restfulapi001.model.dto;
import lombok.Builder;
import java.util.Date;
import java.util.Set;

@Builder
public record ProductResponseDto(
        String uuid,
        String productName,
        Date createDate,
        Date expireDate,
        Boolean isDeleted,
        Set<CategoryResponseDto> categories
) {}
