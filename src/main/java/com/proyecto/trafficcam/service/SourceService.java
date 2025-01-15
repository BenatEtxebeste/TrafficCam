package com.proyecto.trafficcam.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.proyecto.trafficcam.SSLUtils;
import com.proyecto.trafficcam.model.dto.CameraDTO;
import com.proyecto.trafficcam.model.dto.IncidenciaDTO;
import com.proyecto.trafficcam.model.entity.Camera;
import com.proyecto.trafficcam.model.entity.Incidencia;
import com.proyecto.trafficcam.model.entity.Source;
import com.proyecto.trafficcam.model.response.CameraResponse;
import com.proyecto.trafficcam.model.response.IncidenciaResponse;
import com.proyecto.trafficcam.model.response.SourceResponse;
import com.proyecto.trafficcam.repository.CameraRepository;
import com.proyecto.trafficcam.repository.IncidenciaRepository;
import com.proyecto.trafficcam.repository.SourceRepository;

@Service
public class SourceService {

    @Autowired
    private SourceRepository sourceRepository;

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void cargarBBDD() {
        String url = "https://api.euskadi.eus/traffic/v1.0/sources";

        SSLUtils.disableSslVerification();
        SourceResponse[] sources = restTemplate.getForObject(url, SourceResponse[].class);

        for (SourceResponse sourceResponse : sources) {
            Source source = new Source();
            source.setNombre(sourceResponse.getDescripcionEu());
            source.setCameras(null);
            source.setIncidencias(null);

            sourceRepository.save(source);
        }
    }

    public void cargarBBDD2() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String fechaFormateada = fechaActual.format(formatter);

        String url = "https://api.euskadi.eus/traffic/v1.0/incidences/byDate/" + fechaFormateada;

        SSLUtils.disableSslVerification();
        IncidenciaResponse incidenciasResponse = restTemplate.getForObject(url, IncidenciaResponse.class);

        for (int i = 1; i <= incidenciasResponse.getTotalPages(); i++) {
            IncidenciaResponse incidencias = restTemplate.getForObject(url + "?_page=" + i, IncidenciaResponse.class);
            
            for (IncidenciaDTO incidenciaDTO : incidencias.getIncidences()) {
                Incidencia incidencia = new Incidencia();
                incidencia.setAutonomousRegion(incidenciaDTO.getAutonomousRegion());
                incidencia.setCarRegistration(incidenciaDTO.getCarRegistration());
                incidencia.setCause(incidenciaDTO.getCause());
                incidencia.setCityTown(incidenciaDTO.getCityTown());
                incidencia.setDirection(incidenciaDTO.getDirection());
                incidencia.setEndDate(incidenciaDTO.getEndDate());
                incidencia.setIncidenceId(incidenciaDTO.getIncidenceId());
                incidencia.setIncidenceLevel(incidenciaDTO.getIncidenceLevel());
                incidencia.setIncidenceType(incidenciaDTO.getIncidenceType());
                incidencia.setLatitude(incidenciaDTO.getLatitude());
                incidencia.setLongitude(incidenciaDTO.getLongitude());
                incidencia.setPkEnd(incidenciaDTO.getPkEnd());
                incidencia.setPkStart(incidenciaDTO.getPkStart());
                incidencia.setProvince(incidenciaDTO.getProvince());
                incidencia.setRoad(incidenciaDTO.getRoad());
                incidencia.setStartDate(incidenciaDTO.getStartDate());
                Source source = sourceRepository.findById(incidenciaDTO.getSourceId()).orElse(null);
                incidencia.setSource(source);
                source.insertarIncidencia(incidencia);
                incidenciaRepository.save(incidencia);
            }
        }
    }

    public void cargarBBDD3() {
        String url = "https://api.euskadi.eus/traffic/v1.0/cameras";

        SSLUtils.disableSslVerification();
        CameraResponse camerasResponse = restTemplate.getForObject(url, CameraResponse.class);

        for (int i = 1; i <= camerasResponse.getTotalPages(); i++) {
            CameraResponse cameras = restTemplate.getForObject(url + "?_page=" + i, CameraResponse.class);
            
            for (CameraDTO cameraDTO : cameras.getCameras()) {
                Camera camera = new Camera();
                camera.setAddress(cameraDTO.getAddress());
                camera.setCameraName(cameraDTO.getCameraName());
                camera.setKilometer(cameraDTO.getKilometer());
                camera.setLatitude(cameraDTO.getLatitude());
                camera.setLongitude(cameraDTO.getLongitude());
                camera.setRoad(cameraDTO.getRoad());
                camera.setUrlImage(cameraDTO.getUrlImage());
                Source source = sourceRepository.findById(cameraDTO.getSourceId()).orElse(null);
                camera.setSource(source);
                source.insertarCamera(camera);
                cameraRepository.save(camera);
            }
        }
    }

    @EventListener(ContextRefreshedEvent.class)
    public void cargarDatosAlInicio() {
        cargarBBDD();
        cargarBBDD2();
        cargarBBDD3();
    }
}
