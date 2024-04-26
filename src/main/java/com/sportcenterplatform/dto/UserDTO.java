package com.sportcenterplatform.dto;


import com.sportcenterplatform.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

/**
 * Represents a data transfer object (DTO) for user information.
 * This record class provides basic information about users.
 */
@Builder
public record UserDTO(
        @NotBlank(message = "Email is required") @Email(message = "Email is not valid") String email,
        @NotBlank(message = "Username is required") String username,
        Role role
) {}