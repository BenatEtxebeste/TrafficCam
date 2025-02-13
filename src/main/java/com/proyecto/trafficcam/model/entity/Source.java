package com.proyecto.trafficcam.model.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "sources")
public class Source {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "source", fetch = FetchType.EAGER)
    private List<Camera> cameras = new ArrayList<>();

    @OneToMany(mappedBy = "source", fetch = FetchType.EAGER)
    private List<Incidencia> incidencias = new ArrayList<>();

    public void insertarCamera(Camera camera) {
        cameras.add(camera);
    };

    public void insertarIncidencia(Incidencia incidencia) {
        incidencias.add(incidencia);
    }
}
