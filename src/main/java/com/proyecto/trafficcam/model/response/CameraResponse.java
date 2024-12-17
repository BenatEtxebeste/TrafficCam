package com.proyecto.trafficcam.model.response;

import java.util.List;

import com.proyecto.trafficcam.model.entity.Camera;

import lombok.Data;

@Data
public class CameraResponse {
    
    private int totalItems;
    private int totalPages;
    private int currentPage;
    private List<Camera> cameras;
}