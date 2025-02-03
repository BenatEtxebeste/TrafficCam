package com.proyecto.trafficcam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.proyecto.trafficcam.model.entity.Ciudad;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

    Ciudad findByNombre(@Param("nombre") String nombre);

}
