package com.sportcenterplatform.repository;

import com.sportcenterplatform.entity.EventSignup;
import com.sportcenterplatform.entity.Schedule;
import com.sportcenterplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventSignupRepository extends JpaRepository<EventSignup, Long> {
    boolean existsByUserAndSchedule(User user, Schedule schedule);

    List<EventSignup> getEventSignupsByUser(User user);
    void deleteEventSignupByUserAndSchedule(User user, Schedule schedule);
    void deleteEventSignupBySchedule(Schedule schedule);
}
