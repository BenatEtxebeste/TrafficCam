package com.proyecto.trafficcam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.trafficcam.model.dto.IncidenciaDTO;
import com.proyecto.trafficcam.model.entity.Incidencia;
import com.proyecto.trafficcam.model.response.IncidenciaPATCH;
import com.proyecto.trafficcam.repository.IncidenciaRepository;
import com.proyecto.trafficcam.repository.SourceRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Tag(name = "Incidencias")
@AllArgsConstructor
@RestController
public class IncidenciaController {
    
    @Autowired
    IncidenciaRepository incidenciaRepository;

    @Autowired
    SourceRepository sourceRepository;

    @GetMapping("/incidences")
    @Operation(summary = "Recoger incidencias", description = "Devuelve todas las incidencias existentes")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Incidencias encontradas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Incidencia.class)))
	})
	public List<Incidencia> getIncidencias() {
		List<Incidencia> incidences = new ArrayList<Incidencia>();
        incidenciaRepository.findAll().forEach(incidence -> incidences.add(incidence));;
        return incidences;
	}

	@GetMapping("/incidences/{id}")
    @Operation(summary = "Recoger incidencia mediante id", description = "Devuelve la incidencia con la id introducida")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Incidencia encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Incidencia.class)))
	})
	public Incidencia getIncidenciaById(@PathVariable("id") long id) {
		return incidenciaRepository.findById(id).get();
	}

    @GetMapping("/incidences/city")
    @Operation(summary = "Recoger incidencia mediante el nombre de la ciudad", description = "Devuelve la incidencia con el city town introducido")
    @ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Incidencia encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Incidencia.class)))
	})
    public List<Incidencia> getIncidenciasByCityTown(@RequestParam("cityTown") String cityTown) {
    return incidenciaRepository.findByCityTownContaining(cityTown);
}
    

    @PostMapping("/incidences")
    @Operation(summary = "Insertar incidencia", description = "Inserta una nueva incidencia en la base de datos")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Incidencia creada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Incidencia.class)))
	})
    public void saveIncidence(@RequestBody IncidenciaDTO request) {
        Incidencia incidencia = new Incidencia();
        incidencia.setAutonomousRegion(request.getAutonomousRegion());
        incidencia.setCarRegistration(request.getCarRegistration());
        incidencia.setCause(request.getCause());
        incidencia.setCityTown(request.getCityTown());
        incidencia.setCreado(true);
        incidencia.setDirection(request.getDirection());
        incidencia.setEndDate(request.getEndDate());
        incidencia.setIncidenceId(request.getIncidenceId());
        incidencia.setIncidenceLevel(request.getIncidenceLevel());
        incidencia.setIncidenceType(request.getIncidenceType());
        incidencia.setLatitude(request.getLatitude());
        incidencia.setLongitude(request.getLongitude());
        incidencia.setPkEnd(request.getPkEnd());
        incidencia.setPkStart(request.getPkStart());
        incidencia.setProvince(request.getProvince());
        incidencia.setRoad(request.getRoad());
        incidencia.setSource(sourceRepository.getReferenceById(request.getSourceId()));
        incidencia.setStartDate(request.getStartDate());

        incidenciaRepository.save(incidencia);
    }
    
    @PatchMapping("/incidences")
    @Operation(summary = "Modificar incidencia", description = "Modifica la incidencia con la id introducida")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Incidencia modificada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Incidencia.class)))
	})
    public void updateIncidence(@RequestBody IncidenciaPATCH request) {
        Incidencia incidencia = new Incidencia();
        incidencia.setAutonomousRegion(request.getAutonomousRegion());
        incidencia.setCarRegistration(request.getCarRegistration());
        incidencia.setCause(request.getCause());
        incidencia.setCityTown(request.getCityTown());
        incidencia.setDirection(request.getDirection());
        incidencia.setEndDate(request.getEndDate());
        incidencia.setId(request.getId());
        incidencia.setIncidenceId(request.getIncidenceId());
        incidencia.setIncidenceLevel(request.getIncidenceLevel());
        incidencia.setIncidenceType(request.getIncidenceType());
        incidencia.setLatitude(request.getLatitude());
        incidencia.setLongitude(request.getLongitude());
        incidencia.setPkEnd(request.getPkEnd());
        incidencia.setPkStart(request.getPkStart());
        incidencia.setProvince(request.getProvince());
        incidencia.setRoad(request.getRoad());
        incidencia.setSource(sourceRepository.getReferenceById(request.getSourceId()));
        incidencia.setStartDate(request.getStartDate());

        incidenciaRepository.save(incidencia);
    }

    @DeleteMapping("/incidences/{id}")
    @Operation(summary = "Eliminar incidencia", description = "Elimina la incidencia con la id introducida")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Incidencias encontradas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Incidencia.class)))
	})
    public void deleteIncidence(@PathVariable("id") long id) {
        incidenciaRepository.deleteById(id);
    }
}
