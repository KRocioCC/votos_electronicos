package com.example.votos.electronicos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import jakarta.validation.constraints.*;

/**
 * DTO para transferencia de datos de Partido con validaciones.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartidoDTO implements Serializable {

    private Long idPartido;

    @NotBlank(message = "El nombre del partido es obligatorio")
    private String nombrePartido;

    @NotBlank(message = "La sigla del partido es obligatoria")
    @Size(max = 10, message = "La sigla debe tener un máximo de 10 caracteres")
    private String sigla;

    @Size(max = 255, message = "El lema no puede superar los 255 caracteres")
    private String lema;
}
