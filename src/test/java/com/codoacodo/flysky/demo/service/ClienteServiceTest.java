package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.response.ReservaDTO;
import com.codoacodo.flysky.demo.model.entity.ReservaEntity;
import com.codoacodo.flysky.demo.model.entity.UsuarioEntity;
import com.codoacodo.flysky.demo.model.enums.TipoUsuario;

import com.codoacodo.flysky.demo.repository.ButacaRepository;
import com.codoacodo.flysky.demo.repository.ReservaRepository;
import com.codoacodo.flysky.demo.repository.UsuarioRepository;
import com.codoacodo.flysky.demo.repository.VueloRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {
    private ClienteService clienteService;
    private UsuarioRepository usuarioRepository;

  /*  public ClienteServiceTest(UsuarioRepository usuarioRepository,ClienteService clienteService) {
        this.usuarioRepository = usuarioRepository;
        this.clienteService = clienteService;
    }*/


      @BeforeEach
   void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioRepository = Mockito.mock(UsuarioRepository.class);
       clienteService = new ClienteServiceImpl(usuarioRepository);
    }
//NO funciona
    @Test
    @DisplayName("Obtener reservas de cliente - Éxito")
    void obtenerReservasDeClienteExito() {
        // Arrange
        String nombreUsuario = "Carlos";
        String nombreCliente = "Miguel";
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setTipoUsuario(TipoUsuario.AGENTE_DE_VENTAS);
        UsuarioEntity clienteEntity = new UsuarioEntity();
        List<ReservaEntity> reservaEntities = new ArrayList<>();
        reservaEntities.add(new ReservaEntity());
        clienteEntity.setReserva(reservaEntities);
        when(usuarioRepository.getByNombreUsuario(nombreUsuario)).thenReturn(Optional.of(usuarioEntity));
        when(usuarioRepository.getByNombreUsuario(nombreCliente)).thenReturn(Optional.of(clienteEntity));

        // Act
        List<ReservaDTO> reservas = clienteService.obtenerReservasDeCliente(nombreUsuario, nombreCliente);
        System.out.println("reservaEntities.size() = " + reservaEntities.size());
        System.out.println("reservas.size() = " + reservas.size());
        System.out.println("Usuario"+reservas.get(0).getUsuario());
        // Assert
        assertEquals(reservaEntities.size(), reservas.size());
    }
}
