package com.sportcenterplatform.dto;

import com.sportcenterplatform.entity.SportType;
import lombok.Builder;

@Builder
public record SportEventNewDTO(
        SportType sportType,
        String description,
        Long trainerId) {

}
