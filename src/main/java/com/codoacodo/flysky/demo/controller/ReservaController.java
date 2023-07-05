package com.codoacodo.flysky.demo.controller;

import com.codoacodo.flysky.demo.dto.request.ReservaVueloDTO;
import com.codoacodo.flysky.demo.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reserva")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }


    //Ver si es post o put ya que modifica la disponibilidad de butaca y vuelo.
    @PutMapping("/crear/{nombreUsuarioTipoCliente}")
    public ResponseEntity<?> reservarVuelo(@PathVariable String nombreUsuarioTipoCliente,
                                           @RequestBody ReservaVueloDTO reservaVueloDTO) {
        return new ResponseEntity<>(reservaService.reservarVuelo(nombreUsuarioTipoCliente, reservaVueloDTO), HttpStatus.OK);
    }


}


