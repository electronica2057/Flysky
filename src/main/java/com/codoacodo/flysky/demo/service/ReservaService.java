package com.codoacodo.flysky.demo.service;



import com.codoacodo.flysky.demo.dto.request.BusquedaUsuarioDTO;
import com.codoacodo.flysky.demo.dto.request.ReservaRequestDTO;
import com.codoacodo.flysky.demo.dto.response.ReservaDTO;

import java.util.List;

public interface ReservaService {
    List<ReservaDTO> obtenerReservasPorNombreUsuario(BusquedaUsuarioDTO busquedaUsuarioDTO);

    //crear reserva --NUEVO
    ReservaDTO crearReserva (ReservaRequestDTO requestDTO);


}
