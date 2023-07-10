package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.response.VueloDTO;
import com.codoacodo.flysky.demo.exception.EntityNotFoundException;
import com.codoacodo.flysky.demo.model.entity.VueloEntity;
import com.codoacodo.flysky.demo.repository.UsuarioRepository;
import com.codoacodo.flysky.demo.repository.VueloRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VueloServiceImpl implements VueloService {
    private final VueloRepository vueloRepository;
    private final UsuarioRepository usuarioRepository;

    public VueloServiceImpl(VueloRepository vueloRepository, UsuarioRepository usuarioRepository) {
        this.vueloRepository = vueloRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<VueloDTO> obtenerVuelosDisponibles(String nombreUsuario) {
        usuarioRepository.getByNombreUsuario(nombreUsuario).orElseThrow(() -> new NoSuchElementException("Usuario no registrado."));

        List<VueloEntity> entities = vueloRepository.findByDisponibleTrue();

        if (entities.isEmpty()){
            throw new EntityNotFoundException("No se encontraron vuelos disponibles");
        }

        ModelMapper mapper = new ModelMapper();

        return entities.stream().map(entity-> mapper.map(entity, VueloDTO.class)).toList();
    }
}
