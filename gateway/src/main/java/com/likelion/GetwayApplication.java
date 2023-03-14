package com.likelion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GetwayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GetwayApplication.class, args);
    }
}