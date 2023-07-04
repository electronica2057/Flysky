package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.response.VueloDTO;
import com.codoacodo.flysky.demo.exception.EntityNotFoundException;
import com.codoacodo.flysky.demo.model.entity.VueloEntity;
import com.codoacodo.flysky.demo.repository.VueloRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VueloServiceImpl implements VueloService {
    private final VueloRepository vueloRepository;

    public VueloServiceImpl(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    @Override
    public List<VueloDTO> obtenerVuelosDisponibles() {
        List<VueloEntity> entities = vueloRepository.findByDisponibleTrue();

        if (entities.isEmpty()){
            throw new EntityNotFoundException("No se encontraron vuelos disponibles");
        }

        ModelMapper mapper = new ModelMapper();

        return entities.stream().map(entity-> mapper.map(entity, VueloDTO.class)).toList();
    }
}
