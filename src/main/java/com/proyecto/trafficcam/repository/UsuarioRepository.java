package com.proyecto.trafficcam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.trafficcam.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
