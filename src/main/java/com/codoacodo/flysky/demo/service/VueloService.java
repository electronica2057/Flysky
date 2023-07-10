package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.response.VueloDTO;

import java.util.List;

public interface VueloService {
    List<VueloDTO> obtenerVuelosDisponibles(String nombreUsuario);
}
