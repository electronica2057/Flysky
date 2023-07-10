package com.codoacodo.flysky.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String nombreUsuario;
    private Integer telefono;
}
