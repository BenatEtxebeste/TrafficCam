package com.proyecto.trafficcam.model.entity;

// import java.util.ArrayList;
// import java.util.List;

import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ciudades")
public class Ciudad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String imageUrl;

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

    // @OneToMany(mappedBy = "ciudad", fetch = FetchType.EAGER)
    // private List<Incidencia> incidencias = new ArrayList<>();

    // public void insertarIncidencia(Incidencia incidencia) {
    //     incidencias.add(incidencia);
    // }
}
