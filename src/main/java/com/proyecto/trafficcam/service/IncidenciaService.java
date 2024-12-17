package com.proyecto.trafficcam.service;

import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.proyecto.trafficcam.SSLUtils;
import com.proyecto.trafficcam.model.entity.Incidencia;
import com.proyecto.trafficcam.model.response.IncidenciaResponse;
import com.proyecto.trafficcam.repository.IncidenciaRepository;

@Service
public class IncidenciaService {
    
    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void cargarBBDD() {
        String url = "https://api.euskadi.eus/traffic/v1.0/incidences/byDate/2024/12/17";

        SSLUtils.disableSslVerification();
        IncidenciaResponse incidenciasResponse = restTemplate.getForObject(url, IncidenciaResponse.class);

        for (int i = 1; i <= incidenciasResponse.getTotalPages(); i++) {
            IncidenciaResponse incidencias = restTemplate.getForObject(url + "?_page=" + i, IncidenciaResponse.class);
            
            for (Incidencia incidencia : incidencias.getIncidences()) {
                incidenciaRepository.save(incidencia);
            }
        }
    }

    @EventListener(ContextRefreshedEvent.class)
    public void cargarDatosAlInicio() {
        cargarBBDD();
    }
}