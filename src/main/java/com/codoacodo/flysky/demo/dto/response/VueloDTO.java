package com.codoacodo.flysky.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VueloDTO {

        private List<ButacaDTO> butacas;
        private Boolean disponible;
        private Integer capacidad;
        private String aerolinea;
        private LocalDateTime fechaHoraPartida;
        private LocalDateTime fechaHoraLlegada;
        private Double precio;
        private String origen;
        private String destino;
}
