package com.proyecto.trafficcam.model.response;

import java.util.List;

import com.proyecto.trafficcam.model.entity.Incidencia;

import lombok.Data;

@Data
public class IncidenciaResponse {

    private int totalItems;
    private int totalPages;
    private int currentPage;
    private List<Incidencia> incidences;
}
