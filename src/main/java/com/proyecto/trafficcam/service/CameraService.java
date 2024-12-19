package com.proyecto.trafficcam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.proyecto.trafficcam.SSLUtils;
import com.proyecto.trafficcam.model.entity.Camera;
import com.proyecto.trafficcam.model.response.CameraResponse;
import com.proyecto.trafficcam.repository.CameraRepository;

@Service
public class CameraService {
    
    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void cargarBBDD() {
        String url = "https://api.euskadi.eus/traffic/v1.0/cameras";

        SSLUtils.disableSslVerification();
        CameraResponse camerasResponse = restTemplate.getForObject(url, CameraResponse.class);

        for (int i = 1; i <= camerasResponse.getTotalPages(); i++) {
            CameraResponse cameras = restTemplate.getForObject(url + "?_page=" + i, CameraResponse.class);
            
            for (Camera camera : cameras.getCameras()) {
                cameraRepository.save(camera);
            }
        }
    }

    public List<Camera> getCameras() {
        List<Camera> cameras = new ArrayList<Camera>();
        cameraRepository.findAll().forEach(camera -> cameras.add(camera));

        return cameras;
    }

    public Camera getCameraById(long id) {
        return cameraRepository.findById(id).get();
    }

    @EventListener(ContextRefreshedEvent.class)
    public void cargarDatosAlInicio() {
        // cargarBBDD();
    }
}
