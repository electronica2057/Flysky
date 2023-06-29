package com.codoacodo.flysky.demo.dto.response;

import com.codoacodo.flysky.demo.model.enums.TipoPago;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {
    private TipoPago tipoPago;
    private Double montoPagar;
    private LocalDateTime fechaReserva;
    @JsonBackReference
    private UsuarioDTO usuario;
    @JsonBackReference
    private VueloDTO vuelo;
}
