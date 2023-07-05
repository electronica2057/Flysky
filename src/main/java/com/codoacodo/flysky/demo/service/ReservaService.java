package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.request.ReservaVueloDTO;
import com.codoacodo.flysky.demo.dto.response.ReservaVueloResponseDto;
import com.codoacodo.flysky.demo.dto.response.VentaDTO;

import java.time.LocalDate;


public interface ReservaService {

    ReservaVueloResponseDto reservarVuelo(String nombreUsuarioTipoCliente, ReservaVueloDTO
            reservaVueloDTO);

    VentaDTO obtenerNumeroVentasIngresosDiarios(String nombreUsuarioTipoAdministrador, LocalDate fecha);

}