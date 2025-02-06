package com.proyecto.trafficcam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.trafficcam.model.entity.Incidencia;


public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Incidencia i WHERE i.creado = false")
    void eliminarIncidenciasNoCreadas();

    List<Incidencia> findByCityTownContaining(String cityTown);
}
