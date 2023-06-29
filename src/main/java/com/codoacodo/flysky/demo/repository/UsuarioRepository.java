package com.codoacodo.flysky.demo.repository;

import com.codoacodo.flysky.demo.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    UsuarioEntity getByNombreUsuario(String nombreUsuario);
}
