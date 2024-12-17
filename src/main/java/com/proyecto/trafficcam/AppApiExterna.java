package com.proyecto.trafficcam;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppApiExterna {

    @Bean
    protected RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
