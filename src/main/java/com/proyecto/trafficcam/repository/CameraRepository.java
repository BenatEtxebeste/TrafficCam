package com.proyecto.trafficcam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.trafficcam.model.entity.Camera;

public interface CameraRepository extends JpaRepository<Camera, Long> {
    
    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE cameras AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

}
