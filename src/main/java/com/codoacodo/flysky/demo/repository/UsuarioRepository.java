package com.codoacodo.flysky.demo.repository;

import com.codoacodo.flysky.demo.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> getByNombreUsuario(String nombreUsuario);
}
