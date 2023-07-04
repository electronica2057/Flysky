package com.codoacodo.flysky.demo.dto.request;

import com.codoacodo.flysky.demo.model.enums.TipoPago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaRequestDTO {
    private TipoPago tipoPago;
    private Double montoPagar;
    private Long usuarioId;
    private Long vueloId;
}
