package com.codoacodo.flysky.demo.model.enums;

public enum TipoUsuario {
    CLIENTE("Cliente"),
    ADMINISTRADOR("Administrador"),
    AGENTE_DE_VENTAS("Agente de ventas");

    private final String descripcion;

    TipoUsuario(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

