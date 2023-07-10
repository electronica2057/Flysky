package com.codoacodo.flysky.demo.util;

import com.codoacodo.flysky.demo.model.enums.TipoPago;

public class Util {

    public static Double calcularMonto(TipoPago tipoPago, Double precio) {
        return switch (tipoPago) {
            case TARJETA_CREDITO -> precio * 1.10;
            case TARJETA_DEBITO, TRANSFERENCIA_BANCARIA -> precio * 1;
            case PAGO_EN_LINEA -> precio * 1.08;
            case EFECTIVO -> precio * 0.9;
        };
    }

}
