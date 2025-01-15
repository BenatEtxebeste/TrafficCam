package com.proyecto.trafficcam.model.response;

import java.util.List;

import com.proyecto.trafficcam.model.dto.IncidenciaDTO;

import lombok.Data;

@Data
public class IncidenciaResponse {

    private int totalItems;
    private int totalPages;
    private int currentPage;
    private List<IncidenciaDTO> incidences;
}
