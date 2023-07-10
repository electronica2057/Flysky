package com.codoacodo.flysky.demo.dto.response;

import com.codoacodo.flysky.demo.model.enums.TipoPago;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {
    private TipoPago tipoPago;
    private Double montoPago;
    private LocalDate fechaReserva;
    private UsuarioDTO usuario;
    private VueloDTO vuelo;
}
