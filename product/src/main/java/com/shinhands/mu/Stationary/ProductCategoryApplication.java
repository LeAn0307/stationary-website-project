package com.shinhands.mu.Stationary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductCategoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductCategoryApplication.class,args);
    }
}