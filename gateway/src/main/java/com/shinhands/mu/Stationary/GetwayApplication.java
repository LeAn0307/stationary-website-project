package com.shinhands.mu.Stationary;

import io.netty.handler.codec.http.cors.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableEurekaClient

public class GetwayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GetwayApplication.class, args);
    }
}