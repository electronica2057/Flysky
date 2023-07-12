package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.util.Util;
import com.codoacodo.flysky.demo.dto.request.ReservaVueloDTO;
import com.codoacodo.flysky.demo.dto.response.ReservaVueloResponseDto;
import com.codoacodo.flysky.demo.dto.response.VentaDTO;
import com.codoacodo.flysky.demo.exception.EntityNotFoundException;
import com.codoacodo.flysky.demo.exception.UnAuthorizedException;
import com.codoacodo.flysky.demo.model.entity.ButacaEntity;
import com.codoacodo.flysky.demo.model.entity.ReservaEntity;
import com.codoacodo.flysky.demo.model.entity.UsuarioEntity;
import com.codoacodo.flysky.demo.model.entity.VueloEntity;
import com.codoacodo.flysky.demo.model.enums.TipoUsuario;
import com.codoacodo.flysky.demo.repository.ButacaRepository;
import com.codoacodo.flysky.demo.repository.ReservaRepository;
import com.codoacodo.flysky.demo.repository.UsuarioRepository;
import com.codoacodo.flysky.demo.repository.VueloRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {
    private final UsuarioRepository usuarioRepository;
    private final ReservaRepository reservaRepository;
    private final VueloRepository vueloRepository;
    private final ButacaRepository butacaRepository;

    public ReservaServiceImpl(UsuarioRepository usuarioRepository, ReservaRepository reservaRepository, VueloRepository vueloRepository, ButacaRepository butacaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.reservaRepository = reservaRepository;
        this.vueloRepository = vueloRepository;
        this.butacaRepository = butacaRepository;
    }

    @Override
    @Transactional
    public ReservaVueloResponseDto reservarVuelo(String nombreUsuarioTipoCliente, ReservaVueloDTO
            reservaVueloDTO) {
        UsuarioEntity usuario = usuarioRepository.getByNombreUsuario(nombreUsuarioTipoCliente).orElseThrow(() -> new NoSuchElementException("Usuario no registrado. Registrese como Cliente para poder realizar reservas."));

        TipoUsuario tipoUsuario = usuario.getTipoUsuario();

        if (!tipoUsuario.equals(TipoUsuario.CLIENTE)) {
            throw new UnAuthorizedException("Usuario de tipo: " + tipoUsuario.getDescripcion() + "! Registrese como Cliente para realizar una reserva.");
        }

        List<VueloEntity> vuelosDisponibles = vueloRepository.findByDisponibleTrue();

        if (vuelosDisponibles.isEmpty()) {
            throw new EntityNotFoundException("No hay vuelos disponibles en este momento. Intentelo más tarde.");
        }

        VueloEntity vueloAReservar = vuelosDisponibles.stream()
                .filter(vuelo ->
                        vuelo.getAerolinea().equalsIgnoreCase(reservaVueloDTO.getAerolinea()) &
                                vuelo.getFechaHoraPartida().equals(reservaVueloDTO.getFechaHoraPartida()) &
                                vuelo.getFechaHoraLlegada().equals(reservaVueloDTO.getFechaHoraLlegada()) &
                                vuelo.getOrigen().equalsIgnoreCase(reservaVueloDTO.getOrigen()) &
                                vuelo.getDestino().equalsIgnoreCase(reservaVueloDTO.getDestino()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("El vuelo que quiere reservar no está disponible."));

        List<ButacaEntity> butacasDelVuelo = vueloAReservar.getButacas();

        if (butacasDelVuelo.isEmpty()) {
            throw new EntityNotFoundException("El vuelo que quiere reservar no tiene asignadas butacas.");
        }

        ButacaEntity butacaAReservar = butacasDelVuelo
                .stream()
                .filter(butaca -> reservaVueloDTO.getPosicionButaca().equals(butaca.getPosicion()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("La posición de la butaca seleccionada no pertenece al vuelo."));

        if (!butacaAReservar.getDisponible()) {
            throw new EntityNotFoundException("La posición de la butaca seleccionada no está disponible.");
        }

        //Modificamos por FALSE la disponibilidad de la butaca reservada.
        butacaAReservar.setDisponible(Boolean.FALSE);

        butacaRepository.save(butacaAReservar);

        //Modificamos por FALSE la disponibilidad del vuelo si todas las butacas han sido reservadas.
        long cantidadButacasOcupadas = butacasDelVuelo
                .stream()
                .filter(butaca -> butaca.getDisponible().equals(Boolean.FALSE))
                .count();

        if (cantidadButacasOcupadas == vueloAReservar.getCapacidad()) {
            vueloAReservar.setDisponible(Boolean.FALSE);
            vueloRepository.save(vueloAReservar);
        }

        ReservaEntity reservaEntity = new ReservaEntity();
        reservaEntity.setTipoPago(reservaVueloDTO.getTipoPago());
        reservaEntity.setMontoPago(Util.calcularMonto(reservaVueloDTO.getTipoPago(), vueloAReservar.getPrecio()));
        reservaEntity.setFechaReserva(LocalDate.now());
        reservaEntity.setUsuario(usuario);
        reservaEntity.setVuelo(vueloAReservar);
        reservaEntity.setButaca(butacaAReservar);

        reservaRepository.save(reservaEntity);

        ReservaVueloResponseDto reservaVueloResponseDto = new ReservaVueloResponseDto();
        reservaVueloResponseDto.setNombreUsuario(reservaEntity.getUsuario().getNombreUsuario());
        reservaVueloResponseDto.setAerolinea(reservaEntity.getVuelo().getAerolinea());
        reservaVueloResponseDto.setFechaHoraPartida(reservaEntity.getVuelo().getFechaHoraPartida());
        reservaVueloResponseDto.setFechaHoraLlegada(reservaEntity.getVuelo().getFechaHoraLlegada());
        reservaVueloResponseDto.setOrigen(reservaEntity.getVuelo().getOrigen());
        reservaVueloResponseDto.setDestino(reservaEntity.getVuelo().getDestino());
        reservaVueloResponseDto.setPosicionButaca(butacaAReservar.getPosicion());  //Por esta linea no utilizamos un ModelMapper.
        reservaVueloResponseDto.setTipoPago(reservaEntity.getTipoPago());
        reservaVueloResponseDto.setMontoPago(reservaEntity.getMontoPago());
        reservaVueloResponseDto.setFechaReserva(reservaEntity.getFechaReserva());

        return reservaVueloResponseDto;
    }

    @Override
    public VentaDTO obtenerNumeroVentasIngresosDiarios(String nombreUsuarioTipoAdministrador, LocalDate fecha) {
        Optional<UsuarioEntity> usuarioAdministrador = usuarioRepository.getByNombreUsuario(nombreUsuarioTipoAdministrador);

        if (usuarioAdministrador.isEmpty()) {
            throw new NoSuchElementException("Usuario no registrado. Registrese como ADMINISTRADOR para poder visualizar el número de ventas e ingresos generados diarios.");
        }

        TipoUsuario tipoUsuario = usuarioAdministrador.get().getTipoUsuario();

        if (!tipoUsuario.equals(TipoUsuario.ADMINISTRADOR)) {
            throw new UnAuthorizedException("Usuario de tipo " + tipoUsuario.getDescripcion() + "! Registrese como Administrador para poder obtener un informe de ventas.");
        }

        List<ReservaEntity> reservasEntities = reservaRepository.findByFechaReserva(fecha);

        if (reservasEntities.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
            throw new EntityNotFoundException("No hay reservas realizadas el " + fecha.format(formatter) + ".");
        }

        VentaDTO ventaDto = new VentaDTO();

        ventaDto.setFecha(fecha);
        ventaDto.setCantidadVenta(reservasEntities.size());

        double ingresos = reservasEntities
                .stream()
                .reduce(0.0, (accumulator, reserva) -> accumulator + reserva.getMontoPago(), Double::sum);

        ventaDto.setIngreso(ingresos);

        return ventaDto;
    }
}
