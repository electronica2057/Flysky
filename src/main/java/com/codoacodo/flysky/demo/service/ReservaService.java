package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.request.ReservaVueloDTO;


public interface ReservaService {

    ReservaVueloResponseDto reservarVuelo(String nombreUsuarioTipoCliente, ReservaVueloDTO
            reservaVueloDTO);

}