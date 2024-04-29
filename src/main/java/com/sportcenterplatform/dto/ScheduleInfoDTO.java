package com.sportcenterplatform.dto;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record ScheduleInfoDTO(
        Long id,
        LocalDateTime timeStart,
        LocalDateTime timeEnd,
        Integer signedUpCount,
        Integer capacity
        ) {
}
