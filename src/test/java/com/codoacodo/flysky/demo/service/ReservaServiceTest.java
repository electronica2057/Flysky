package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.request.ReservaVueloDTO;
import com.codoacodo.flysky.demo.dto.response.ReservaVueloResponseDto;
import com.codoacodo.flysky.demo.model.enums.TipoPago;
import com.codoacodo.flysky.demo.repository.ReservaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReservaServiceTest {
    @Autowired
    ReservaService reservaService;

    @Autowired
    ReservaRepository reservaRepository;

    @Test
    @DisplayName("Camino feliz obtener reserva...")
    void obtenerReservasPorNombreUsuarioOkTest(){
        //arrange
        ReservaVueloDTO reservaVueloDTO = new ReservaVueloDTO("Aerolineas Argentinas", LocalDateTime.of(2023, 6, 25, 23, 53, 30), LocalDateTime.of(2023, 6, 25, 23, 53, 30), "Buenos Aires", "Uruguay", "AE08", TipoPago.EFECTIVO);
        //ReservaVueloDTO expected2 = new ReservaVueloDTO("Aerolineas Argentinas", LocalDateTime.of(2023, 6, 25, 23, 53, 30), LocalDateTime.of(2023, 6, 25, 23, 53, 30), "Buenos Aires", "Uruguay", "AE04", TipoPago.EFECTIVO);
        ReservaVueloResponseDto expected = new ReservaVueloResponseDto("Miguel","Aerolineas Argentinas",LocalDateTime.of(2023, 6, 25, 23, 53, 30),LocalDateTime.of(2023, 6, 25, 23, 53, 30),"Buenos Aires","Uruguay","AE08",TipoPago.EFECTIVO,13500.00, LocalDate.now());
        //act
        ReservaVueloResponseDto act = reservaService.reservarVuelo("Miguel", reservaVueloDTO);

        //assert
        assertEquals(expected, act);
    }
}
