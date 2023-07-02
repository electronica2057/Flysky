package com.codoacodo.flysky.demo.controller;

import com.codoacodo.flysky.demo.dto.request.BusquedaUsuarioDTO;
import com.codoacodo.flysky.demo.dto.response.ReservaDTO;
import com.codoacodo.flysky.demo.model.enums.TipoPago;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
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

        TipoPago tipoPago1 = TipoPago.TARJETA_CREDITO;
        TipoPago tipoPago2 = TipoPago.TRANSFERENCIA_BANCARIA;
        TipoPago tipoPago3 = TipoPago.EFECTIVO;
        LocalDateTime fecha1 = LocalDateTime.of(2023, 6, 23, 23, 53,30);
        LocalDateTime fecha2 = LocalDateTime.of(2023, 6, 29, 18, 33,20);
        LocalDateTime fecha3 = LocalDateTime.of(2023, 6, 29, 18, 33,20);
        System.out.println("fecha3 = " + fecha3);
        List<ReservaDTO> reservas = new ArrayList<>();


        ReservaDTO testPostDto= new ReservaDTO(tipoPago1,1500.00, fecha1,null,null);
        ReservaDTO testPostDto2= new ReservaDTO(tipoPago2,2000.00, null,null,null);
        ReservaDTO testPostDto3= new ReservaDTO(tipoPago3,3000.00, null,null,null);

        reservas.add(testPostDto);
        reservas.add(testPostDto2);
        reservas.add(testPostDto3);

        BusquedaUsuarioDTO responseDto = new BusquedaUsuarioDTO("Carlos","Miguel");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE,false)
                .writer();
;
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
