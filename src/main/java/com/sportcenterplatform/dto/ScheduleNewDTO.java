package com.sportcenterplatform.dto;

import java.time.LocalDateTime;

public record ScheduleNewDTO(
        LocalDateTime startTime,
        LocalDateTime endTime,
        Integer capacity
) {
}
