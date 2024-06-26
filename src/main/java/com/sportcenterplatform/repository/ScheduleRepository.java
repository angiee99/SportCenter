package com.sportcenterplatform.repository;

import com.sportcenterplatform.entity.Schedule;
import com.sportcenterplatform.entity.SportsEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> getSchedulesBySportsEvent(SportsEvent event);
}
