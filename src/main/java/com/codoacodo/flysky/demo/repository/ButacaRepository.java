package com.codoacodo.flysky.demo.repository;

import com.codoacodo.flysky.demo.model.entity.ButacaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ButacaRepository extends JpaRepository<ButacaEntity, Long> {
}
