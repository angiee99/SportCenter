package com.sportcenterplatform.dto;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record ScheduleInfoDTO(
        Long id,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Integer signedUpCount,
        Integer capacity
        ) {
}
