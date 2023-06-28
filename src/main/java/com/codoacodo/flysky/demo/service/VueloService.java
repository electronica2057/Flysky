package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.response.VueloDTO;
import com.codoacodo.flysky.demo.model.entity.VueloEntity;

import java.util.List;

public interface VueloService {
    List<VueloDTO> obtenerVuelosDisponibles();
}
