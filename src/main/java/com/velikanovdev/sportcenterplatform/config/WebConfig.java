package com.velikanovdev.sportcenterplatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

/**
 * Configuration class for web-related settings.
 */
@Configuration
@EnableWebMvc
public class WebConfig {
    private static final Long MAX_AGE = 3600L;
    private static final String ALLOWED_ORIGIN = "http://localhost:8001";

    /**
     * Creates a CorsConfigurationSource bean to configure CORS settings.
     *
     * @return the CorsConfigurationSource bean
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", getCorsConfiguration()); // Assuming getCorsConfiguration returns a CorsConfiguration
        return source;
    }

    /**
     * Retrieves a CorsConfiguration object with CORS settings.
     *
     * @return the CorsConfiguration object
     */
    private static CorsConfiguration getCorsConfiguration() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(ALLOWED_ORIGIN);
        config.setAllowedHeaders(List.of(
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT));
        config.setAllowedMethods(List.of(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name()));
        config.setMaxAge(MAX_AGE);
        return config;
    }
}
