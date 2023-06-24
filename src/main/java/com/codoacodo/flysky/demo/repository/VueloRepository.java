package com.codoacodo.flysky.demo.repository;

import com.codoacodo.flysky.demo.model.entity.VueloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VueloRepository extends JpaRepository<VueloEntity, Long> {
    List<VueloEntity> findByDisponibleTrue();
}
