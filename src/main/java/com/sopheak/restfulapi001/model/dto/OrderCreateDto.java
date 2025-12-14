package com.sopheak.restfulapi001.model.dto;
import lombok.Builder;
import java.util.Set;

@Builder
public record OrderCreateDto(
        String orderName,
        String location,
        Set<String> productUuids,
        String customerUuid
){}
