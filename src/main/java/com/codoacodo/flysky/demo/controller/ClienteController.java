package com.codoacodo.flysky.demo.controller;

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
    public ResponseEntity<?> verReservasDeUsuario(@RequestParam String nombreUsuario, @RequestParam String nombreCliente) {
        return new ResponseEntity<>(clienteService.obtenerReservasDeCliente(nombreUsuario, nombreCliente), HttpStatus.OK);
    }
}
