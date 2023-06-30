package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.model.enums.TipoUsuario;

public interface UsuarioService {
    TipoUsuario obtenerTipoUsuarioPorNombreUsuario(String nombreUsuario);
}
