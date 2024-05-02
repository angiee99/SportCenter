package com.sportcenterplatform.service;

import com.sportcenterplatform.dto.SportsEventInfoDTO;
import com.sportcenterplatform.entity.SportsEvent;

import java.util.List;

public interface SportsEventService {
    List<SportsEventInfoDTO> getAllSportsEvents();

    SportsEventInfoDTO getSportsEventById(Long id);
    SportsEvent getSportsEventEntityById(Long id);
}
