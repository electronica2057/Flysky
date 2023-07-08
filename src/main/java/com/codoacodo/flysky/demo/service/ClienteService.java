package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.response.ReservaDTO;

import java.util.List;

public interface ClienteService {
    List<ReservaDTO> obtenerReservasDeCliente(String nombreUsuario, String nombreCliente);
}
