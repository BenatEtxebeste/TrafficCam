package com.proyecto.trafficcam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.trafficcam.model.entity.Ciudad;
import com.proyecto.trafficcam.repository.CiudadRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Ciudades")
@RequiredArgsConstructor
@RestController
public class CiudadController {

	@Autowired
	private CiudadRepository ciudadRepository;

    @GetMapping("/ciudades")
	@Operation(summary = "Recoger ciudades", description = "Devuelve todas las ciudades existentes")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Ciudades encontradas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ciudad.class)))
	})
	public List<Ciudad> getCiudades() {
		List<Ciudad> ciudades = new ArrayList<Ciudad>();
        ciudadRepository.findAll().forEach(ciudad -> ciudades.add(ciudad));

        return ciudades;	
	}

	@GetMapping("/ciudades/{id}")
	@Operation(summary = "Recoger ciudad mediante id", description = "Devuelve la ciudad con la id introducida")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Ciudad encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ciudad.class)))
	})
	public Ciudad getCiudadById(@PathVariable("id") Long id) {
		return ciudadRepository.findById(id).get();
	}
	
}
