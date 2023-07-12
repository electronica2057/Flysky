package com.codoacodo.flysky.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "butaca")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ButacaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean disponible = true;

    private String posicion;

    @OneToOne(mappedBy = "butaca")
    private ReservaEntity reserva;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vuelo_id", nullable = false)
    private VueloEntity vuelo;

}
