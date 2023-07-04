/*package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.response.ReservaDTO;
import com.codoacodo.flysky.demo.model.enums.TipoPago;
import com.codoacodo.flysky.demo.repository.ReservaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

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
        ReservaDTO reservaDTO = new ReservaDTO(TipoPago.TARJETA_CREDITO, 1500.00, LocalDateTime.of(2023, 06, 25 , 23, 53 , 30 ), 1, 1);
        ReservaDTO expected = new ReservaDTO(TipoPago.TARJETA_CREDITO, 1500.00, LocalDateTime.of(2023, 06, 25 , 23, 53 , 30 ), 1, 1);
       //act
        //ReservaDTO act = ReservaService.crearReserva(reservaDTO);
    }
}
*/