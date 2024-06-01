package com.sportcenterplatform.dto;

import com.sportcenterplatform.entity.SportType;
import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record ScheduleAndEventInfoDTO(
        Long id,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Integer signedUpCount,
        Integer capacity,
        SportType sportType,
        String description,
        String trainerEmail
) {}
