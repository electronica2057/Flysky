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
    public List<ReservaDTO> obtenerReservasPorNombreUsuario(BusquedaUsuarioDTO busqueda) {
        Optional<UsuarioEntity> agenteEntity = usuarioRepository.getByNombreUsuario(busqueda.getNombreAgente());

        if (agenteEntity.isEmpty()) {
            throw new EntityNotFoundException("Agente no encontrado");
        }

        TipoUsuario tipoAgente = agenteEntity.get().getTipoUsuario();

        if (!tipoAgente.equals(TipoUsuario.AGENTE_DE_VENTAS)) {
            throw new UnauthorizedException("El usuario no está autorizado para visualizar las reservas");
        }

        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.getByNombreUsuario(busqueda.getNombreUsuario());

        if (usuarioEntity.isEmpty()){
            throw new EntityNotFoundException("Usuario no encontrado");
        }

        List<ReservaEntity> reservaEntities = reservaRepository.getAllByUsuario(usuarioEntity.get());

        if (reservaEntities.isEmpty()){
            throw new EntityNotFoundException("Reservas no encontradas");
        }

        ModelMapper mapper = new ModelMapper();

        return reservaEntities.stream().map(reservaEntity -> mapper.map(reservaEntity, ReservaDTO.class)).toList();
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
