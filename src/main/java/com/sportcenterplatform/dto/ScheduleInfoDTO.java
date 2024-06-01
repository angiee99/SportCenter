package com.sportcenterplatform.dto;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record ScheduleInfoDTO(
        Long id,
        String startTime,
        String endTime,
        Integer signedUpCount,
        Integer capacity
        ) {
}
