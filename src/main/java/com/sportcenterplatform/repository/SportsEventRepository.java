package com.sportcenterplatform.repository;

import com.sportcenterplatform.entity.SportsEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportsEventRepository extends JpaRepository<SportsEvent, Long> {
}
