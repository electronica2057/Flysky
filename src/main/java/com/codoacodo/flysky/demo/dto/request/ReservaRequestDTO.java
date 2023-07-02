package com.codoacodo.flysky.demo.dto.request;

import com.codoacodo.flysky.demo.model.enums.TipoPago;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaRequestDTO {

    private TipoPago tipoPago;
    private Double montoPagar;
    private Long usuarioId;
    private Long vueloId;
}
