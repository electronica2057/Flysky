package com.codoacodo.flysky.demo.service;



import com.codoacodo.flysky.demo.dto.request.ReservaRequestDTO;
import com.codoacodo.flysky.demo.dto.response.ReservaDTO;

import java.util.List;

public interface ReservaService {
    List<ReservaDTO> obtenerReservasPorNombreUsuario(String nombreUsuario);

    //crear reserva --NUEVO
    ReservaDTO crearReserva (ReservaRequestDTO requestDTO);


}
