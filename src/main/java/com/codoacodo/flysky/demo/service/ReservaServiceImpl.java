package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.request.ReservaRequestDTO;
import com.codoacodo.flysky.demo.dto.response.ReservaDTO;
import com.codoacodo.flysky.demo.exception.EntityNotFoundException;
import com.codoacodo.flysky.demo.model.entity.ButacaEntity;
import com.codoacodo.flysky.demo.model.entity.ReservaEntity;
import com.codoacodo.flysky.demo.model.entity.UsuarioEntity;
import com.codoacodo.flysky.demo.model.entity.VueloEntity;
import com.codoacodo.flysky.demo.model.enums.TipoUsuario;
import com.codoacodo.flysky.demo.repository.ReservaRepository;
import com.codoacodo.flysky.demo.repository.UsuarioRepository;
import com.codoacodo.flysky.demo.repository.VueloRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
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

    @Override
    public ReservaVueloResponseDto reservarVuelo(String nombreUsuarioTipoCliente, ReservaVueloDto
            reservaVueloDto) {

        Optional<UsuarioEntity> usuario = usuarioRepository.findByNombreUsuario(nombreUsuarioTipoCliente);

        if (usuario.isPresent()) {
            //if (usuario.get().getTipoUsuario().getDescripcion().equalsIgnoreCase("Cliente"))
            if (usuario.get().getTipoUsuario().equals(TipoUsuario.CLIENTE)) {

                List<VueloEntity> vuelosDisponibles = vueloRepository.findByDisponibleTrue();
                //si la disponibilidad de todos los registro de la tabla vuelo es false, la base de datos va a retornar
                // una lista de vuelos vacía sin lanzar una excepción.
                if (vuelosDisponibles.isEmpty()) {
                    throw new EntityNotFoundException("No hay vuelos disponibles en este momento. Intente más tarde.");
                }

                Optional<VueloEntity> vueloDisponibleReserva = vuelosDisponibles.stream()
                        .filter(vueloDisponible ->
                                vueloDisponible.getAerolinea().equalsIgnoreCase(reservaVueloDto.getAerolinea()) &
                                        vueloDisponible.getFechaHoraPartida().equals(reservaVueloDto.getFechaHoraPartida()) &
                                        vueloDisponible.getFechaHoraLlegada().equals(reservaVueloDto.getFechaHoraLlegada()) &
                                        vueloDisponible.getOrigen().equalsIgnoreCase(reservaVueloDto.getOrigen()) &
                                        vueloDisponible.getDestino().equalsIgnoreCase(reservaVueloDto.getDestino())
                        ).findFirst();
                //PREGUNTAR SI SE DEBE HACER PORQUE EN EL FRONT TENDRIAMOS OPCIONES PARA SELECCIONAR DE LO QUE HAY
                // DISPONIBLE Y NO PARA RELLENAR
                if (vueloDisponibleReserva.isEmpty()) {
                    throw new NoSuchElementException("El vuelo que quiere reservar no está disponible.");
                }

                List<ButacaEntity> butacasVueloDisponibleReserva = vueloDisponibleReserva.get().getButacas();

                if (butacasVueloDisponibleReserva.isEmpty()) {
                    throw new EntityNotFoundException("El vuelo que quiere reservar no tiene asignadas butacas.");
                }

                Optional<ButacaEntity> butacaVueloDisponibleReserva = butacasVueloDisponibleReserva.stream()
                        .filter(butaca -> reservaVueloDto.getPosicionButaca().equals(butaca.getPosicion()))
                        .findFirst();
                //PREGUNTAR SI SE DEBE HACER PORQUE EN EL FRONT TENDRIAMOS OPCIONES PARA SELECCIONAR DE LO QUE HAY
                // DISPONIBLE Y NO PARA RELLENAR
                if (butacaVueloDisponibleReserva.isEmpty()) {
                    throw new NoSuchElementException("La posición de la butaca seleccionada no pertenece " +
                            "al vuelo.");
                }
                //PREGUNTAR SI SE DEBE HACER PORQUE EN EL FRONT TENDRIAMOS OPCIONES PARA SELECCIONAR DE LO QUE HAY
                // DISPONIBLE Y NO PARA RELLENAR
                if (!butacaVueloDisponibleReserva.get().getDisponible()) {
                    throw new EntityNotFoundException("La posición de la butaca seleccionada no está disponible.");
                }

                //Modificamos por FALSE la disponibilidad de la butaca reservada.
                //id de la butaca a reservar.
                Long id = butacaVueloDisponibleReserva.get().getId();

                ButacaEntity butacaPersitencia = new ButacaEntity();
                butacaPersitencia.setId(id);
                butacaPersitencia.setDisponible(Boolean.FALSE);
                butacaPersitencia.setPosicion(butacaVueloDisponibleReserva.get().getPosicion());
                butacaPersitencia.setVuelo(butacaVueloDisponibleReserva.get().getVuelo());

                butacaRepository.save(butacaPersitencia);

                //Modificamos por FALSE la disponibilidad del vuelo si todas las butacas han sido reservadas.
                List<ButacaEntity> butacasNoDisponibles = butacasVueloDisponibleReserva.stream()
                        .filter(butaca -> butaca.getDisponible().equals(Boolean.FALSE))
                        .toList();


                if (butacasNoDisponibles.size() == vueloDisponibleReserva.get().getCapacidad()) {

                    VueloEntity vueloDisponibleReservaPersistencia = new VueloEntity();
                    vueloDisponibleReservaPersistencia.setId(vueloDisponibleReserva.get().getId());
                    vueloDisponibleReservaPersistencia.setDisponible(Boolean.FALSE);
                    vueloDisponibleReservaPersistencia.setCapacidad(vueloDisponibleReserva.get().getCapacidad());
                    vueloDisponibleReservaPersistencia.setAerolinea(vueloDisponibleReserva.get().getAerolinea());
                    vueloDisponibleReservaPersistencia.setFechaHoraPartida(vueloDisponibleReserva.get().getFechaHoraPartida());
                    vueloDisponibleReservaPersistencia.setFechaHoraLlegada(vueloDisponibleReserva.get().getFechaHoraLlegada());
                    vueloDisponibleReservaPersistencia.setPrecio(vueloDisponibleReserva.get().getPrecio());
                    vueloDisponibleReservaPersistencia.setOrigen(vueloDisponibleReserva.get().getOrigen());
                    vueloDisponibleReservaPersistencia.setDestino(vueloDisponibleReserva.get().getDestino());

                    vueloRepository.save(vueloDisponibleReservaPersistencia);
                }

                Double montoPago = Util.montoAPagar(reservaVueloDto.getTipoPago(), vueloDisponibleReserva.get().getPrecio());

                ReservaEntity reservaEntityPersistencia = new ReservaEntity();
                reservaEntityPersistencia.setTipoPago(reservaVueloDto.getTipoPago());
                reservaEntityPersistencia.setMontoPago(montoPago);
                reservaEntityPersistencia.setFechaReserva(LocalDate.now());
                reservaEntityPersistencia.setUsuario(usuario.get());
                reservaEntityPersistencia.setVuelo(vueloDisponibleReserva.get());

                ReservaEntity reservaEntity = reservaRepository.save(reservaEntityPersistencia);

                ReservaVueloResponseDto reservaVueloResponseDto = new ReservaVueloResponseDto();
                reservaVueloResponseDto.setNombreUsuario(reservaEntityPersistencia.getUsuario().getNombreUsuario());
                reservaVueloResponseDto.setAerolinea(reservaEntityPersistencia.getVuelo().getAerolinea());
                reservaVueloResponseDto.setFechaHoraPartida(reservaEntityPersistencia.getVuelo().getFechaHoraPartida());
                reservaVueloResponseDto.setFechaHoraLlegada(reservaEntityPersistencia.getVuelo().getFechaHoraLlegada());
                reservaVueloResponseDto.setOrigen(reservaEntityPersistencia.getVuelo().getOrigen());
                reservaVueloResponseDto.setDestino(reservaEntityPersistencia.getVuelo().getDestino());
                reservaVueloResponseDto.setPosicionButaca(butacaPersitencia.getPosicion());  //Por esta linea no utilizamos un ModelMapper.
                reservaVueloResponseDto.setTipoPago(reservaEntityPersistencia.getTipoPago());
                reservaVueloResponseDto.setMontoPago(reservaEntityPersistencia.getMontoPago());
                reservaVueloResponseDto.setFechaReserva(reservaEntityPersistencia.getFechaReserva());

                return reservaVueloResponseDto;

            }
            throw new UnAuthorizedException("Usuario registrado pero NO AUTORIZADO para poder realizar reservas. " +
                    "Registrese como CLIENTE.");
        }
        throw new NoSuchElementException("Usuario no registrado. Registrese como CLIENTE para poder realizar reservas.");
    }
}
