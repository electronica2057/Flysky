package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.model.entity.VueloEntity;
import com.codoacodo.flysky.demo.repository.VueloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VueloServiceImpl implements VueloService {
    private final VueloRepository vueloRepository;

    public VueloServiceImpl(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    @Override
    public List<VueloEntity> obtenerVuelosDisponibles() {
        return vueloRepository.findByDisponibleTrue();
    }
}
