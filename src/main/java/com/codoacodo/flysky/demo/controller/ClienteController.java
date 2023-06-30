package com.codoacodo.flysky.demo.controller;

import com.codoacodo.flysky.demo.dto.request.BusquedaUsuarioDTO;
import com.codoacodo.flysky.demo.model.enums.TipoUsuario;
import com.codoacodo.flysky.demo.service.ReservaService;
import com.codoacodo.flysky.demo.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {
    private final UsuarioService usuarioService;
    private final ReservaService reservaService;

    public ClienteController(UsuarioService usuarioService, ReservaService reservaService) {
        this.usuarioService = usuarioService;
        this.reservaService = reservaService;
    }

    @GetMapping("/reservas")
    public ResponseEntity<?> verReservasDeUsuario(@RequestBody BusquedaUsuarioDTO busqueda) {
        TipoUsuario tipoAgente = usuarioService.obtenerTipoUsuarioPorNombreUsuario(busqueda.getNombreAgente());

        if (!tipoAgente.equals(TipoUsuario.AGENTE_DE_VENTAS)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(reservaService.obtenerReservasPorNombreUsuario(busqueda.getNombreUsuario()), HttpStatus.OK);
    }

}
