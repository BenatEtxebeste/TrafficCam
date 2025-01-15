package com.proyecto.trafficcam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.trafficcam.model.entity.Incidencia;
import com.proyecto.trafficcam.repository.IncidenciaRepository;

@Service
public class IncidenciaService {
    
    @Autowired
    private IncidenciaRepository incidenciaRepository;

    public List<Incidencia> getIncidences() {
        List<Incidencia> incidences = new ArrayList<Incidencia>();
        incidenciaRepository.findAll().forEach(incidence -> incidences.add(incidence));;
        return incidences;
    }

    public Incidencia getIncidenceById(long id) {
        return incidenciaRepository.findById(id).get();
    }

    public void createIncidence(Incidencia incidence) {
        incidenciaRepository.save(incidence);
    }

    public void deleteIncidence(long id) {
        incidenciaRepository.deleteById(id);
    }

    public void updateIncidence(Incidencia incidence){
        incidenciaRepository.save(incidence);
    }
}