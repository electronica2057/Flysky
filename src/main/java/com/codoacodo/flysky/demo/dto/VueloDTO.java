package com.codoacodo.flysky.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VueloDTO {
    private Boolean disponible;
    private Integer capacidad;
    private String aerolinea;
    private LocalDateTime fechaHoraPartida;
    private LocalDateTime fechaHoraLlegada;
    private Double precio;
    private String origen;
    private String destino;
}
