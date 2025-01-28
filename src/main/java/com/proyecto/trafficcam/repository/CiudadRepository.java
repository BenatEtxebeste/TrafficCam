package com.proyecto.trafficcam.repository;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

import com.proyecto.trafficcam.model.entity.Ciudad;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

    // @Query("SELECT c FROM Ciudad c WHERE c.nombre = :nombre")
    // Optional<Ciudad> findByNombre(@Param("nombre") String nombre);

}
