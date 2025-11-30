package com.sopheak.restfulapi001.model.dto;
import lombok.Builder;
import java.util.Date;

@Builder
public record CategoryResponseDto(
        String uuid,
        String categoryName,
        Date createdDate,
        Boolean isDeleted
){}
