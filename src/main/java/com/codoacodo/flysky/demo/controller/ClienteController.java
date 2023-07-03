package com.codoacodo.flysky.demo.controller;

import com.codoacodo.flysky.demo.dto.request.BusquedaUsuarioDTO;
import com.codoacodo.flysky.demo.dto.request.ReservaRequestDTO;
import com.codoacodo.flysky.demo.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    private final ReservaService reservaService;

    public ClienteController( ReservaService reservaService) {

        this.reservaService = reservaService;
    }

    @GetMapping("/reservas")
    public ResponseEntity<?> verReservasDeUsuario(@RequestBody BusquedaUsuarioDTO busqueda) {


        return new ResponseEntity<>(reservaService.obtenerReservasPorNombreUsuario(busqueda), HttpStatus.OK);
    }

    @PostMapping("/nuevaReserva")
    public ResponseEntity<?> crearReserva(@RequestBody ReservaRequestDTO reservaDTO){
        return new ResponseEntity<>(reservaService.crearReserva(reservaDTO),HttpStatus.OK);
    }


}
