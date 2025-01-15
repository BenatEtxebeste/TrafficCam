package com.proyecto.trafficcam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.trafficcam.model.entity.Source;

public interface SourceRepository extends JpaRepository<Source, Long> {
    
}
