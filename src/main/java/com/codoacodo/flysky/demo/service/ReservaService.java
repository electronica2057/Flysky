package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.response.ReservaDTO;

import java.util.List;

public interface ReservaService {
    List<ReservaDTO> obtenerReservasPorNombreUsuario(String nombreUsuario);
}
