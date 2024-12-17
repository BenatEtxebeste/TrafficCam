package com.proyecto.trafficcam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.trafficcam.model.entity.Incidencia;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {
    
}
