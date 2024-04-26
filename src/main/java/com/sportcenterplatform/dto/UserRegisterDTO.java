package com.sportcenterplatform.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

/**
 * Represents a data transfer object (DTO) for user registration information.
 * This record class provides information required for user registration.
 */
@Builder
public record UserRegisterDTO(
        @NotBlank(message = "Email is required") @Email(message = "Email is not valid") String email,
        @NotBlank(message = "Username is required") String username,
        @NotBlank(message = "Password is required") String password
) {}
