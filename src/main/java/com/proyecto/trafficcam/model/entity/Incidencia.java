package com.proyecto.trafficcam.model.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "incidencias")
public class Incidencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean creado;
    private int incidenceId;
    private String incidenceType;
    private String autonomousRegion;
    private String province;
    private String carRegistration;
    private String cause;
    private String cityTown;
    private Date startDate;
    private Date endDate;
    private String incidenceLevel;
    private String road;
    private double pkStart;
    private double pkEnd;
    private String direction;
    private double latitude;
    private double longitude;

    @ManyToOne
    @JsonIgnoreProperties({"cameras", "incidencias"})
    private Source source;

    @ManyToOne
    @JsonIgnoreProperties({"incidencias"})
    private Ciudad ciudad;
}