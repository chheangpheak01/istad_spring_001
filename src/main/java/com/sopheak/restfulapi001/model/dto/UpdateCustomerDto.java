package com.sopheak.restfulapi001.model.dto;
import jakarta.validation.constraints.*;
import lombok.NonNull;

public record UpdateCustomerDto(
        @NonNull
        @NotBlank(message = "Customer name cannot be blank")
        String customerName,
        @Email(message = "Email format is invalid")
        String email
){}
