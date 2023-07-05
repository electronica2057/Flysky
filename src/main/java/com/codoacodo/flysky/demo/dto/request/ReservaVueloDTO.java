package com.codoacodo.flysky.demo.dto.request;

import com.codoacodo.flysky.demo.model.enums.TipoPago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservaVueloDTO {

    private String aerolinea;
    private LocalDateTime fechaHoraPartida;
    private LocalDateTime fechaHoraLlegada;
    private String origen;
    private String destino;

    private String posicionButaca;

    private TipoPago tipoPago;

}







