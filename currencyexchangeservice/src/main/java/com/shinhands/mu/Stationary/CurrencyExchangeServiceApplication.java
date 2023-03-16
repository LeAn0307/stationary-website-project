package com.shinhands.mu.Stationary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CurrencyExchangeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CurrencyExchangeServiceApplication.class,args);
    }
}