package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.model.entity.UsuarioEntity;
import com.codoacodo.flysky.demo.model.enums.TipoUsuario;
import com.codoacodo.flysky.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public TipoUsuario obtenerTipoUsuarioPorNombreUsuario(String nombreUsuario) {
        UsuarioEntity usuarioEntity = usuarioRepository.getByNombreUsuario(nombreUsuario);
        TipoUsuario tipoUsuario = usuarioEntity.getTipoUsuario();
        return tipoUsuario;
    }
}
