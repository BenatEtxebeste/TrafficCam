package com.proyecto.trafficcam.model.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    
    private Long id;
    private String name;
    private String password;
    private boolean isAdmin;
    
}
