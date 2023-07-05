package com.codoacodo.flysky.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaDTO {

    private LocalDate fecha;
    private Integer cantidadVenta;
    private Double ingreso;

}
