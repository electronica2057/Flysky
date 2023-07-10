package com.codoacodo.flysky.demo.model.entity;

import com.codoacodo.flysky.demo.model.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    private String nombreUsuario;
    private Integer telefono;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ReservaEntity> reserva;
}
