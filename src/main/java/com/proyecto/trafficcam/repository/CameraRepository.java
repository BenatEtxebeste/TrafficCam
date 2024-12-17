package com.proyecto.trafficcam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.trafficcam.model.entity.Camera;

public interface CameraRepository extends JpaRepository<Camera, Long> {
    
}
