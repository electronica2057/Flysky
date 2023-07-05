package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.request.ReservaVueloDTO;
import com.codoacodo.flysky.demo.dto.response.ReservaVueloResponseDto;


public interface ReservaService {

    ReservaVueloResponseDto reservarVuelo(String nombreUsuarioTipoCliente, ReservaVueloDTO
            reservaVueloDTO);

}