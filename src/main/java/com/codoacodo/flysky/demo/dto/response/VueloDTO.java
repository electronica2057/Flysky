package com.codoacodo.flysky.demo.dto.response;

import com.codoacodo.flysky.demo.model.entity.ButacaEntity;
import com.codoacodo.flysky.demo.model.entity.ReservaEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VueloDTO {




        private List<ReservaDTO> reservas;


        private List<ButacaDTO> butacas;

        private Boolean disponible;
        private Integer capacidad;
        private String aerolinea;
        private LocalDateTime horarioPartida;
        private LocalDateTime horarioLlegada;
        private Double precio;
        private String origen;
        private String destino;
}
