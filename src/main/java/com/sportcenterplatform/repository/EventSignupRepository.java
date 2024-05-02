package com.sportcenterplatform.repository;

import com.sportcenterplatform.entity.EventSignup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventSignupRepository extends JpaRepository<EventSignup, Long> {
}
