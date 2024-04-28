package com.sportcenterplatform.dto;

import com.sportcenterplatform.entity.SportType;
import lombok.Builder;

@Builder
public record SportsEventInfoDTO(
        Long id,
        SportType sportType,
        Boolean isAvailable,
        String description,
        String trainerName,
        String trainerEmail
){}
