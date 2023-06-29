package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.response.ReservaDTO;
import com.codoacodo.flysky.demo.model.entity.ReservaEntity;
import com.codoacodo.flysky.demo.model.entity.UsuarioEntity;
import com.codoacodo.flysky.demo.repository.ReservaRepository;
import com.codoacodo.flysky.demo.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {
    private final UsuarioRepository usuarioRepository;
    private final ReservaRepository reservaRepository;

    public ReservaServiceImpl(UsuarioRepository usuarioRepository, ReservaRepository reservaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.reservaRepository = reservaRepository;
    }

    @Override
    public List<ReservaDTO> obtenerReservasPorNombreUsuario(String nombreUsuario) {
        ModelMapper mapper = new ModelMapper();
        UsuarioEntity usuarioEntity = usuarioRepository.getByNombreUsuario(nombreUsuario);
        List<ReservaEntity> reservaEntities = reservaRepository.getAllByUsuario(usuarioEntity);
        List<ReservaDTO> reservaDTOS = reservaEntities.stream().map(reservaEntity -> mapper.map(reservaEntity, ReservaDTO.class)).toList();
        return reservaDTOS;
    }
}
