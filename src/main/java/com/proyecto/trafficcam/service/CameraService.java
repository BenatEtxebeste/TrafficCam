package com.proyecto.trafficcam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.trafficcam.model.entity.Camera;
import com.proyecto.trafficcam.repository.CameraRepository;

@Service
public class CameraService {
    
    @Autowired
    private CameraRepository cameraRepository;

    public List<Camera> getCameras() {
        List<Camera> cameras = new ArrayList<Camera>();
        cameraRepository.findAll().forEach(camera -> cameras.add(camera));

        return cameras;
    }

    public Camera getCameraById(long id) {
        return cameraRepository.findById(id).get();
    }
}
