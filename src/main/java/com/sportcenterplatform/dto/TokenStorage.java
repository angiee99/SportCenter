package com.sportcenterplatform.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TokenStorage {
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
