package com.codoacodo.flysky.demo.controller;

import com.codoacodo.flysky.demo.dto.request.BusquedaUsuarioDTO;
import com.codoacodo.flysky.demo.dto.response.ReservaDTO;
import com.codoacodo.flysky.demo.model.enums.TipoPago;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        List<ReservaDTO> reservas = new ArrayList<>();

        ReservaDTO testPostDto= new ReservaDTO(TipoPago.TARJETA_CREDITO,1500.00, LocalDateTime.of(2023, 6, 25, 23, 53,30),null,null);
        ReservaDTO testPostDto2= new ReservaDTO(TipoPago.TRANSFERENCIA_BANCARIA,2000.00, LocalDateTime.of(2023, 6, 29, 18, 33,20),null,null);
        ReservaDTO testPostDto3= new ReservaDTO(TipoPago.EFECTIVO,3000.00, LocalDateTime.of(2023, 6, 29, 18, 33,20),null,null);

        reservas.add(testPostDto);
        reservas.add(testPostDto2);
        reservas.add(testPostDto3);

        BusquedaUsuarioDTO responseDto = new BusquedaUsuarioDTO("Carlos","Miguel");

        ObjectMapper writer = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        String jsonPayload = writer.writeValueAsString(responseDto);
        System.out.println("reservas = " + reservas);
        String responseJson = writer.writeValueAsString(reservas);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/clientes/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        assertEquals(responseJson,mvcResult.getResponse().getContentAsString());
    }



}
