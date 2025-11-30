package com.sopheak.restfulapi001.model.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record CreateCustomerDto(
        @NonNull
        @NotBlank(message = "Customer name cannot be blank")
        String customerName,
        @Email(message = "Email format is invalid")
        String email,
        @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
                 message = "Password must contain at least 8 characters, including uppercase, lowercase, number, and special character")
        String password
){}
