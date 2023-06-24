package com.codoacodo.flysky.demo.model.enums;

public enum TipoPago {
    TARJETA_CREDITO("Tarjeta de crédito"),
    TARJETA_DEBITO("Tarjeta de débito"),
    TRANSFERENCIA_BANCARIA("Transferencia bancaria"),
    PAGO_EN_LINEA("Pago en línea"),
    EFECTIVO("Efectivo");

    private final String descripcion;

    TipoPago(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
