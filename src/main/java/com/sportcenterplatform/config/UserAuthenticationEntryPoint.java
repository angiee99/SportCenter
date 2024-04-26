package com.sportcenterplatform.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Custom implementation of Spring Security's AuthenticationEntryPoint interface.
 * This class handles unauthorized requests by sending an HTTP 401 Unauthorized response.
 */
@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * Invoked when an unauthenticated user attempts to access a secured resource.
     *
     * @param request       the HTTP servlet request
     * @param response      the HTTP servlet response
     * @param authException the authentication exception that occurred
     * @throws IOException if an I/O error occurs during the handling of the authentication entry point
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        // this may be the place where it always sends 401
    }
}
