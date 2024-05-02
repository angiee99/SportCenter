package com.sportcenterplatform.service;

import com.sportcenterplatform.dto.ScheduleInfoDTO;
import com.sportcenterplatform.entity.SportsEvent;

import java.util.List;

public interface ScheduleService {
    List<ScheduleInfoDTO> getAllBySportsEventId(SportsEvent event);
}
