package com.sopheak.restfulapi001.model.dto;
import lombok.Builder;

@Builder
public record UserResponseDto(
        String uuid,
        String name,
        String email
){}
