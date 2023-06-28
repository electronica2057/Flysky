package com.codoacodo.flysky.demo.dto.response;

import com.codoacodo.flysky.demo.model.entity.VueloEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ButacaDTO {
    private Boolean disponible;

    private String posicion;


}
