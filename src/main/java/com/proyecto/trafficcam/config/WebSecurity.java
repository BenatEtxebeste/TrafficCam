package com.proyecto.trafficcam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurity {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilita CSRF para evitar restricciones en solicitudes POST, PUT, DELETE
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/v3/api-docs/**", // Endpoints de documentación OpenAPI
                    "/swagger-ui/**",  // Swagger UI
                    "/index.html", // Página principal de Swagger
                    "/api/**"          // Rutas de tu API
                ).permitAll() // Permite acceso sin autenticación a las rutas especificadas
                .anyRequest().permitAll() // Permite acceso a cualquier otra ruta
            )
            .httpBasic(httpBasic -> httpBasic.disable()) // Deshabilita la autenticación básica
            .formLogin(formLogin -> formLogin.disable()); // Deshabilita el formulario de login

        return http.build();
    }
}
