package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.response.ReservaClienteDTO;
import com.codoacodo.flysky.demo.model.entity.ReservaEntity;
import com.codoacodo.flysky.demo.model.entity.UsuarioEntity;
import com.codoacodo.flysky.demo.model.entity.VueloEntity;
import com.codoacodo.flysky.demo.model.enums.TipoPago;
import com.codoacodo.flysky.demo.model.enums.TipoUsuario;
import com.codoacodo.flysky.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {
    private ClienteService clienteService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clienteService = new ClienteServiceImpl(usuarioRepository);
    }

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
        ReservaEntity reserva1 = new ReservaEntity();
        reserva1.setTipoPago(TipoPago.EFECTIVO);
        reserva1.setMontoPago(1500.0);
        reserva1.setFechaReserva(LocalDate.of(2023,6,25));
        reserva1.setUsuario(clienteEntity);
        reserva1.setVuelo(createVueloEntity());
        reservaEntities.add(reserva1);
        clienteEntity.setReserva(reservaEntities);
        when(usuarioRepository.getByNombreUsuario(nombreUsuario)).thenReturn(Optional.of(usuarioEntity));
        when(usuarioRepository.getByNombreUsuario(nombreCliente)).thenReturn(Optional.of(clienteEntity));

        // Act
        List<ReservaClienteDTO> reservas = clienteService.obtenerReservasDeCliente(nombreUsuario, nombreCliente);

        // Assert
        assertEquals(reservaEntities.size(), reservas.size());
        assertEquals(reserva1.getTipoPago(), reservas.get(0).getTipoPago());
        assertEquals(reserva1.getMontoPago(), reservas.get(0).getMontoPago());
        assertEquals(reserva1.getFechaReserva(), reservas.get(0).getFechaReserva());
        assertEquals(reserva1.getVuelo().getCapacidad(), reservas.get(0).getVuelo().getCapacidad());
        assertEquals(reserva1.getVuelo().getAerolinea(), reservas.get(0).getVuelo().getAerolinea());
        assertEquals(reserva1.getVuelo().getFechaHoraPartida(), reservas.get(0).getVuelo().getFechaHoraPartida());
        assertEquals(reserva1.getVuelo().getFechaHoraLlegada(), reservas.get(0).getVuelo().getFechaHoraLlegada());
        assertEquals(reserva1.getVuelo().getPrecio(), reservas.get(0).getVuelo().getPrecio());
        assertEquals(reserva1.getVuelo().getOrigen(), reservas.get(0).getVuelo().getOrigen());
        assertEquals(reserva1.getVuelo().getDestino(), reservas.get(0).getVuelo().getDestino());
    }

    private VueloEntity createVueloEntity() {
        VueloEntity vueloEntity = new VueloEntity();
        vueloEntity.setButacas(new ArrayList<>());
        vueloEntity.setDisponible(false);
        vueloEntity.setCapacidad(50);
        vueloEntity.setAerolinea("Aerolineas Argentinas");
        vueloEntity.setFechaHoraPartida(LocalDateTime.of(2023, 6, 25, 23, 53, 30));
        vueloEntity.setFechaHoraLlegada(LocalDateTime.of(2023, 6, 25, 23, 53, 30));
        vueloEntity.setPrecio(15000.0);
        vueloEntity.setOrigen("Buenos Aires");
        vueloEntity.setDestino("Uruguay");
        return vueloEntity;
    }
}
