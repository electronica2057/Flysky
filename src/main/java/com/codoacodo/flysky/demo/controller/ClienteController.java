package com.codoacodo.flysky.demo.controller;

import com.codoacodo.flysky.demo.dto.request.BusquedaUsuarioDTO;
import com.codoacodo.flysky.demo.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/reservas")
    public ResponseEntity<?> verReservasDeUsuario(@RequestBody BusquedaUsuarioDTO busqueda) {
        return new ResponseEntity<>(clienteService.obtenerReservasDeCliente(busqueda), HttpStatus.OK);
    }

    /*
    Pasarlo a su propio controlador de reservas
    @PostMapping("/nuevaReserva")
    public ResponseEntity<?> crearReserva(@RequestBody ReservaRequestDTO reservaDTO){
        return new ResponseEntity<>(clienteService.crearReserva(reservaDTO),HttpStatus.OK);
    }
    */
}
