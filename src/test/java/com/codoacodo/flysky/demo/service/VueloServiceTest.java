package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.response.VueloDTO;
import com.codoacodo.flysky.demo.model.entity.VueloEntity;
import com.codoacodo.flysky.demo.repository.VueloRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VueloServiceTest {

    @Test
    @DisplayName("US0001-ListaDeVuelosDisponibles")
    void buscarVuelosOkTest() {
        // Arrange
        VueloRepository vueloRepository = Mockito.mock(VueloRepository.class);
        VueloService vueloService = new VueloServiceImpl(vueloRepository);

        List<VueloEntity> vueloEntities = new ArrayList<>();
        VueloEntity vueloEntity1 = new VueloEntity();
        vueloEntity1.setDisponible(true);
        vueloEntity1.setCapacidad(50);
        vueloEntity1.setAerolinea("Aerolineas Argentinas");
        vueloEntity1.setFechaHoraPartida((LocalDateTime.parse("2023-06-25T23:53:30")));
        vueloEntity1.setFechaHoraPartida((LocalDateTime.parse("2023-06-25T23:53:30")));
        vueloEntity1.setPrecio(15000.0);
        vueloEntity1.setOrigen("Buenos Aires");
        vueloEntity1.setDestino("Uruguay");
        vueloEntities.add(vueloEntity1);

        VueloEntity vueloEntity2 = new VueloEntity();
        vueloEntity2.setDisponible(true);
        vueloEntity2.setCapacidad(50);
        vueloEntity2.setAerolinea("Aerolineas Uruguayas");
        vueloEntity2.setFechaHoraPartida(LocalDateTime.parse("2023-06-25T23:53:30"));
        vueloEntity2.setFechaHoraPartida((LocalDateTime.parse("2023-06-25T23:53:30")));
        vueloEntity2.setPrecio(15000.0);
        vueloEntity2.setOrigen("Buenos Aires");
        vueloEntity2.setDestino("Uruguay");
        vueloEntities.add(vueloEntity2);

        when(vueloRepository.findByDisponibleTrue()).thenReturn(vueloEntities);

        // Act
        List<VueloDTO> vuelosObtenidos = vueloService.obtenerVuelosDisponibles();

        // Assert
        assertEquals(2, vuelosObtenidos.size());
        assertEquals(vueloEntity1.getAerolinea(), vuelosObtenidos.get(0).getAerolinea());
        assertEquals(vueloEntity2.getAerolinea(), vuelosObtenidos.get(1).getAerolinea());

        //verify(vueloRepository, times(1)).findByDisponibleTrue();
    }
}
