package com.codoacodo.flysky.demo.service;


import com.codoacodo.flysky.demo.dto.response.ReservaClienteDTO;
import com.codoacodo.flysky.demo.dto.response.ReservaDTO;
import com.codoacodo.flysky.demo.exception.EntityNotFoundException;
import com.codoacodo.flysky.demo.exception.UnAuthorizedException;
import com.codoacodo.flysky.demo.model.entity.ReservaEntity;
import com.codoacodo.flysky.demo.model.entity.UsuarioEntity;
import com.codoacodo.flysky.demo.model.enums.TipoUsuario;
import com.codoacodo.flysky.demo.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final UsuarioRepository usuarioRepository;

    public ClienteServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<ReservaClienteDTO> obtenerReservasDeCliente(String nombreUsuario, String nombreCliente) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.getByNombreUsuario(nombreUsuario);

        if (usuarioEntity.isEmpty()) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }

        TipoUsuario tipoUsuario = usuarioEntity.get().getTipoUsuario();

        if (!tipoUsuario.equals(TipoUsuario.AGENTE_DE_VENTAS)) {
            throw new UnAuthorizedException("El usuario no está autorizado para visualizar las reservas");
        }

        Optional<UsuarioEntity> clienteEntity = usuarioRepository.getByNombreUsuario(nombreCliente);

        if (clienteEntity.isEmpty()){
            throw new EntityNotFoundException("Cliente no encontrado");
        }

        List<ReservaEntity> reservaEntities = clienteEntity.get().getReserva();

        if (reservaEntities.isEmpty()){
            throw new EntityNotFoundException("Reservas no encontradas");
        }

        ModelMapper mapper = new ModelMapper();

        return reservaEntities.stream().map(reservaEntity -> mapper.map(reservaEntity, ReservaClienteDTO.class)).toList();
    }
}
