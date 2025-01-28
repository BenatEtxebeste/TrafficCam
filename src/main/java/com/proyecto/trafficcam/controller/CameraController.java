package com.proyecto.trafficcam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.trafficcam.model.entity.Camera;
import com.proyecto.trafficcam.repository.CameraRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Camaras")
@RequiredArgsConstructor
@RestController
public class CameraController {

	@Autowired
	private CameraRepository cameraRepository;

    @GetMapping("/cameras")
	@Operation(summary = "Recoger camaras", description = "Devuelve todas las camaras existentes")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Camaras encontradas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Camera.class)))
	})
	public List<Camera> getCameras() {
		List<Camera> cameras = new ArrayList<Camera>();
        cameraRepository.findAll().forEach(camera -> cameras.add(camera));

        return cameras;	
	}

	@GetMapping("/cameras/{id}")
	@Operation(summary = "Recoger camara mediante id", description = "Devuelve la camara con la id introducida")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Camara encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Camera.class)))
	})
	public Camera getCameraById(@PathVariable("id") Long id) {
		return cameraRepository.findById(id).get();
	}
	
}