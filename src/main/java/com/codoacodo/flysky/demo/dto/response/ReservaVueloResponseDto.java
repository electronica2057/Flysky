package com.codoacodo.flysky.demo.dto.response;

import com.codoacodo.flysky.demo.model.enums.TipoPago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaVueloResponseDto {

    private String nombreUsuario;
    private String aerolinea;
    private LocalDateTime fechaHoraPartida;
    private LocalDateTime fechaHoraLlegada;
    private String origen;
    private String destino;
    private String posicionButaca;

    private TipoPago tipoPago;
    private double montoPago;
    private LocalDate fechaReserva;
}
