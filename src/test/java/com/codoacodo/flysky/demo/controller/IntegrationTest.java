package com.codoacodo.flysky.demo.controller;

import com.codoacodo.flysky.demo.dto.response.*;
import com.codoacodo.flysky.demo.model.enums.TipoPago;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    public void testReservas() throws Exception {
        List<ReservaClienteDTO> reservas = new ArrayList<>();

        ReservaClienteDTO testReserva1 = new ReservaClienteDTO(
                TipoPago.TARJETA_CREDITO,
                1500D,
                LocalDate.of(2023,6,25),
                new com.codoacodo.flysky.demo.dto.VueloDTO(true, 50,"Aerolineas Argentinas", LocalDateTime.of(2023, 6, 25, 23, 53, 30),LocalDateTime.of(2023, 6, 25, 23, 53, 30), 15000.0, "Buenos Aires", "Uruguay"),
                new ButacaDTO(false, "AE04")
                );
        ReservaClienteDTO testReserva2 = new ReservaClienteDTO(
                TipoPago.TRANSFERENCIA_BANCARIA,
                2000D,
                LocalDate.of(2023,6,29),
                new com.codoacodo.flysky.demo.dto.VueloDTO(false, 50, "Aerolineas Argentinas", LocalDateTime.of(2023, 6, 25, 23, 53, 30), LocalDateTime.of(2023, 6, 25, 23, 53, 30), 15000.0, "Buenos Aires", "Uruguay"),
                new ButacaDTO(false, "AE05")
        );
        ReservaClienteDTO testReserva3 = new ReservaClienteDTO(
                TipoPago.EFECTIVO,
                3000D,
                LocalDate.of(2023,6,29),
                new com.codoacodo.flysky.demo.dto.VueloDTO(false, 50, "Aerolineas Argentinas", LocalDateTime.of(2023, 6, 25, 23, 53, 30), LocalDateTime.of(2023, 6, 25, 23, 53, 30), 15000.0, "Buenos Aires", "Uruguay"),
                new ButacaDTO(false, "AE06")
        );

        reservas.add(testReserva1);
        reservas.add(testReserva2);
        reservas.add(testReserva3);

        ObjectMapper writer = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        String nombreUsuario = "Carlos";
        String nombreCliente = "Miguel";

        String responseJson = writer.writeValueAsString(reservas);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/clientes/reservas")
                        .param("nombreUsuario", nombreUsuario)
                        .param("nombreCliente", nombreCliente))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        assertEquals(responseJson,mvcResult.getResponse().getContentAsString());
    }

    @Test
    void validacionCreacionUsuario() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/reserva/ventas")
                        .param("nombreUsuario", "Juan")
                        .param("fecha",  "2023-06-29"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
               // .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Juan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ingreso").value(5000.0))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void validacionVuelosDisponibles() throws Exception {
        mockMvc.perform(get("/api/v1/vuelos/disponibles")
                        .param("nombreUsuario", "Juan"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].aerolinea").value("Aerolineas Argentinas"));
    }





}
