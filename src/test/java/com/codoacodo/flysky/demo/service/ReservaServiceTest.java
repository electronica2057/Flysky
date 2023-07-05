/*package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.request.ReservaRequestDTO;
import com.codoacodo.flysky.demo.dto.response.ReservaDTO;
import com.codoacodo.flysky.demo.model.enums.TipoPago;
import com.codoacodo.flysky.demo.repository.ReservaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReservaServiceTest {

    @Autowired
    ReservaServiceTest reservaService;

    @Autowired
    ReservaRepository reservaRepository;

    @Test
    @DisplayName("Camino feliz obtener reserva...")
    void obtenerReservasPorNombreUsuarioOkTest(){
        //arrange
        ReservaRequestDTO reservaRequestDTO = new ReservaRequestDTO(TipoPago.TARJETA_CREDITO, 1500.00, 1L, 1L);
        ReservaDTO expected = new ReservaDTO(TipoPago.TARJETA_CREDITO, 1500.00, LocalDateTime.of(2023, 06, 25 , 23, 53 , 30),null,null);
       //act

        ReservaDTO act = reservaService.crearReserva(reservaRequestDTO);

        //assert
        assertEquals(expected,act);
    }
}*/
