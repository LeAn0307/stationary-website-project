package com.shinhands.mu.Stationary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan

public class StationaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(StationaryApplication.class, args);
	}

}
