package com.codoacodo.flysky.demo.dto.response;

import com.codoacodo.flysky.demo.dto.VueloDTO;
import com.codoacodo.flysky.demo.model.enums.TipoPago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaClienteDTO {
    private TipoPago tipoPago;
    private Double montoPago;
    private LocalDate fechaReserva;
    private VueloDTO vuelo;
    private ButacaDTO butaca;
}
