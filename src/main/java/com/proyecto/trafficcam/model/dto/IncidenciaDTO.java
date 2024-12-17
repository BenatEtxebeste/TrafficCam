package com.proyecto.trafficcam.model.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class IncidenciaDTO {
    
    private int incidenceId;
    private int sourceId;
    private String incidenceType;
    private String autonomousRegion;
    private String province;
    private String carRegistration;
    private String cause;
    private String cityTown;
    private Date startDate;
    private String incidenceLevel;
    private String road;
    private double pkStart;
    private double pkEnd;
    private String direction;
    private double latitude;
    private double longitude;
    
}
