package com.sportcenterplatform.service;

import com.sportcenterplatform.dto.SportEventNewDTO;
import com.sportcenterplatform.dto.SportsEventInfoDTO;
import com.sportcenterplatform.entity.SportsEvent;

import java.util.List;

public interface SportsEventService {
    List<SportsEventInfoDTO> getAllSportsEvents();

    SportsEventInfoDTO getSportsEventById(Long id);
    SportsEvent getSportsEventEntityById(Long id);

    void save(SportEventNewDTO event);

    void delete(Long id);

    void updateAvailability(Long id);
}
