package com.sopheak.restfulapi001.model.dto;
import lombok.Builder;

@Builder
public record CreateUserDto(
        String name,
        String email,
        String password
){}
