package com.proyecto.trafficcam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TrafficcamApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficcamApplication.class, args);
	}

}
