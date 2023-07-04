package com.codoacodo.flysky.demo.repository;

import com.codoacodo.flysky.demo.model.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
}
