package com.proyecto.trafficcam.model.dto;

import lombok.Data;

@Data
public class CameraDTO {

    private Long id;
    private int sourceId;
    private String cameraName;
    private double latitude;
    private double longitude;
    private String road;
    private String kilometer;
    private String address;
    private String urlImage;
    
}
