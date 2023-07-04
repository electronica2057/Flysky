package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.request.ReservaRequestDTO;
import com.codoacodo.flysky.demo.dto.response.ReservaDTO;

public interface ReservaService {
    //crear reserva --NUEVO
    ReservaDTO crearReserva(ReservaRequestDTO requestDTO);

}