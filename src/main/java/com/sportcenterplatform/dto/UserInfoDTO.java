package com.sportcenterplatform.dto;

/**
 * Represents a data transfer object (DTO) for user information.
 * This record class provides basic information about users.
 */
public record UserInfoDTO(
        Long id,
        String username
) {}
