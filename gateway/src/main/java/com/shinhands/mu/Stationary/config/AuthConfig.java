package com.shinhands.mu.Stationary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AuthConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
