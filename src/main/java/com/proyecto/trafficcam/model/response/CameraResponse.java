package com.proyecto.trafficcam.model.response;

import java.util.List;

import com.proyecto.trafficcam.model.dto.CameraDTO;

import lombok.Data;

@Data
public class CameraResponse {
    
    private int totalItems;
    private int totalPages;
    private int currentPage;
    private List<CameraDTO> cameras;
}