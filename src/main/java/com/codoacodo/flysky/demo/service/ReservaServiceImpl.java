package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.request.BusquedaUsuarioDTO;
import com.codoacodo.flysky.demo.dto.request.ReservaRequestDTO;
import com.codoacodo.flysky.demo.dto.response.ReservaDTO;
import com.codoacodo.flysky.demo.exception.EntityNotFoundException;
import com.codoacodo.flysky.demo.exception.UnauthorizedException;
import com.codoacodo.flysky.demo.model.entity.ReservaEntity;
import com.codoacodo.flysky.demo.model.entity.UsuarioEntity;
import com.codoacodo.flysky.demo.model.entity.VueloEntity;
import com.codoacodo.flysky.demo.model.enums.TipoUsuario;
import com.codoacodo.flysky.demo.repository.ReservaRepository;
import com.codoacodo.flysky.demo.repository.UsuarioRepository;
import com.codoacodo.flysky.demo.repository.VueloRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {
    private final UsuarioRepository usuarioRepository;
    private final ReservaRepository reservaRepository;
    private final VueloRepository vueloRepository;

    public ReservaServiceImpl(UsuarioRepository usuarioRepository, ReservaRepository reservaRepository, VueloRepository vueloRepository) {
        this.usuarioRepository = usuarioRepository;
        this.reservaRepository = reservaRepository;
        this.vueloRepository = vueloRepository;
    }

    @Override
    public List<ReservaDTO> obtenerReservasPorNombreUsuario(BusquedaUsuarioDTO busquedaUsuarioDTO) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.getByNombreUsuario(busquedaUsuarioDTO.getNombreAgente());

        if (usuarioEntity.isEmpty()) {

            throw new RuntimeException();
        }
        TipoUsuario tipoAgente = usuarioEntity.get().getTipoUsuario();

        if (!tipoAgente.equals(TipoUsuario.AGENTE_DE_VENTAS)) {
            throw new UnauthorizedException("El usuario no está autorizado para visualizar las reservas");

        }
        ModelMapper mapper = new ModelMapper();
        Optional<UsuarioEntity> usuario = usuarioRepository.getByNombreUsuario(busquedaUsuarioDTO.getNombreUsuario());
        if (usuario.isEmpty()){
            throw new EntityNotFoundException("Usuario no encontrado");

        }
        List<ReservaEntity> reservaEntities = reservaRepository.getAllByUsuario(usuario.get());
        if (reservaEntities.isEmpty()){
            throw new EntityNotFoundException("Reserva no encontrada");
        }
        List<ReservaDTO> reservaDTOS = reservaEntities.stream().map(reservaEntity -> mapper.map(reservaEntity, ReservaDTO.class)).toList();
        return reservaDTOS;
    }

    @Override
    public ReservaDTO crearReserva(ReservaRequestDTO reservaRequestDTO) {
        ModelMapper mapper = new ModelMapper();
        UsuarioEntity usuarioEntity = usuarioRepository.findById(reservaRequestDTO.getUsuarioId()).orElse(null);
        VueloEntity vueloEntity = vueloRepository.findById(reservaRequestDTO.getVueloId()).orElse(null);
        ReservaEntity reserva = crearReservaEntity(reservaRequestDTO, vueloEntity, usuarioEntity);
        ReservaEntity reservaRes = reservaRepository.save(reserva);
        ReservaDTO ReservaDtoRes = mapper.map(reservaRes, ReservaDTO.class);

        return ReservaDtoRes;
    }

    private ReservaEntity crearReservaEntity (ReservaRequestDTO reservaRequestDTO, VueloEntity vueloEntity, UsuarioEntity usuarioEntity){
        ReservaEntity reservaEntity = new ReservaEntity();
        reservaEntity.setMontoPagar(reservaRequestDTO.getMontoPagar());
        reservaEntity.setTipoPago(reservaRequestDTO.getTipoPago());
        reservaEntity.setUsuario(usuarioEntity);
        reservaEntity.setVuelo(vueloEntity);
        return reservaEntity;

    }
}
