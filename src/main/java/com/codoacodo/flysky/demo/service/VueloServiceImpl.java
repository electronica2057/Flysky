package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.response.VueloDTO;
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
        ModelMapper mapper = new ModelMapper();
        List<VueloEntity> entities = vueloRepository.findByDisponibleTrue();

        if (entities.isEmpty()){
            throw new RuntimeException("No hay vuelos disponibles");
        }
        List<VueloDTO> dtos = entities.stream().map(entity-> mapper.map(entity, VueloDTO.class)).toList();
        return dtos;
    }
}
