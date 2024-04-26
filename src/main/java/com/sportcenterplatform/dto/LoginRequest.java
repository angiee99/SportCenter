package com.sportcenterplatform.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents a data transfer object (DTO) for user login requests.
 * This record class provides information required for user login.
 */
public record LoginRequest(
        @NotBlank(message = "Email is required") @Email(message = "Email is not valid") String email,
        @NotBlank(message = "Password is required") String password
) {}

