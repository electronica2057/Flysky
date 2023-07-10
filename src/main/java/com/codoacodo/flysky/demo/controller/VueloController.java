package com.codoacodo.flysky.demo.controller;

import com.codoacodo.flysky.demo.service.VueloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/vuelos")
public class VueloController {
    private final VueloService vueloService;

    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @GetMapping("/disponibles")
    public ResponseEntity<?> verListaDeVuelosDisponibles(@RequestParam String nombreUsuario) {
        return new ResponseEntity<>(vueloService.obtenerVuelosDisponibles(nombreUsuario), HttpStatus.OK);
    }


}
