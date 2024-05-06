package com.sportcenterplatform.service;

public interface EventSignupService {
    void signup(Long userId, Long scheduleId);
    boolean isSignedUp(Long userId, Long scheduleId);
}
