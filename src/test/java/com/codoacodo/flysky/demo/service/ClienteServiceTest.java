package com.codoacodo.flysky.demo.service;

import com.codoacodo.flysky.demo.dto.response.ReservaDTO;
import com.codoacodo.flysky.demo.model.entity.UsuarioEntity;
import com.codoacodo.flysky.demo.repository.ReservaRepository;
import com.codoacodo.flysky.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ClienteServiceTest {
    @Autowired
    ClienteService clienteService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Cliente de esta reserva")
    void obtenerReservasDeClienteOkTest() {
        //arrange
        UsuarioRepository usuarioRepository = new UsuarioRepository() {
            @Override
            public Optional<UsuarioEntity> getByNombreUsuario(String nombreUsuario) {
                return Optional.empty();
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends UsuarioEntity> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends UsuarioEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<UsuarioEntity> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public UsuarioEntity getOne(Long aLong) {
                return null;
            }

            @Override
            public UsuarioEntity getById(Long aLong) {
                return null;
            }

            @Override
            public UsuarioEntity getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends UsuarioEntity> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends UsuarioEntity> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public <S extends UsuarioEntity> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public List<UsuarioEntity> findAll() {
                return null;
            }

            @Override
            public List<UsuarioEntity> findAllById(Iterable<Long> longs) {
                return null;
            }

            @Override
            public <S extends UsuarioEntity> S save(S entity) {
                return null;
            }

            @Override
            public Optional<UsuarioEntity> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(UsuarioEntity entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends UsuarioEntity> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public List<UsuarioEntity> findAll(Sort sort) {
                return null;
            }

            @Override
            public Page<UsuarioEntity> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends UsuarioEntity> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends UsuarioEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends UsuarioEntity> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends UsuarioEntity> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends UsuarioEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
    }
    //act
   // List<ReservaDTO> reserva = clienteService.obtenerReservasDeCliente("Mariano", "Mariano");
    //assert
    //assertEquals(expected, act);

    }




