package com.proyecto.trafficcam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.trafficcam.model.entity.Camera;
import com.proyecto.trafficcam.service.CameraService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequiredArgsConstructor
@RestController
public class CameraController {
    
	@Autowired
	private CameraService cameraService;

    @GetMapping("/cameras")
	public List<Camera> getCameras() {
		return cameraService.getCameras();	
	}

	@GetMapping("/cameras/{id}")
	public Camera getCameraById(@PathVariable("id") int id) {
		return cameraService.getCameraById(id);
	}
	
    
}
