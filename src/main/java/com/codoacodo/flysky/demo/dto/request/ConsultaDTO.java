package com.codoacodo.flysky.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDTO {
    private String nombreAgente;
    private String nombreUsuario;
}
