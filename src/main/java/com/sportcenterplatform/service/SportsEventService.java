package com.sportcenterplatform.service;

import com.sportcenterplatform.dto.SportsEventInfoDTO;

import java.util.List;

public interface SportsEventService {
    List<SportsEventInfoDTO> getAllSportsEvents();

    SportsEventInfoDTO getSportsEventById(Long id);
}
