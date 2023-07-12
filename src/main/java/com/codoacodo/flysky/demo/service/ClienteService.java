package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.response.ReservaClienteDTO;
import com.codoacodo.flysky.demo.dto.response.ReservaDTO;

import java.util.List;

public interface ClienteService {
    List<ReservaClienteDTO> obtenerReservasDeCliente(String nombreUsuario, String nombreCliente);
}
