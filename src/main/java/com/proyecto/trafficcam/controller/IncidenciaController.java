package com.proyecto.trafficcam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.trafficcam.model.entity.Incidencia;
import com.proyecto.trafficcam.service.IncidenciaService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@AllArgsConstructor
@RestController
public class IncidenciaController {
    
    @Autowired
    IncidenciaService incidenciaService;

    @GetMapping("/incidences")
	public List<Incidencia> getIncidencias() {
		return incidenciaService.getIncidences();	
	}

	@GetMapping("/incidences/{id}")
	public Incidencia getIncidenciaById(@PathVariable("id") long id) {
		return incidenciaService.getIncidenceById(id);
	}

    @PostMapping("/incidences")
    public void saveIncidence(@RequestBody Incidencia incidencia) {
        incidenciaService.createIncidence(incidencia);
    }
    
    @PutMapping("/incidences")
    public void updateIncidence(@RequestBody Incidencia incidencia) {
        incidenciaService.updateIncidence(incidencia);
    }

    @DeleteMapping("/incidences/{id}")
    public void deleteIncidence(@PathVariable("id") long id) {
        incidenciaService.deleteIncidence(id);
    }

}
