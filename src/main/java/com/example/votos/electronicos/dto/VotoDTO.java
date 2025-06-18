package com.example.votos.electronicos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import jakarta.validation.constraints.*;

/**
 * DTO para transferencia de datos de Voto con validaciones.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VotoDTO implements Serializable {

    private Long idVoto;

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Long idEstudiante;  // ID del estudiante que vota (referencia a Estudiante)

    private Long idDocente;  // ID del docente que vota (si aplica)

    @NotNull(message = "El ID del partido es obligatorio")
    private Long idPartido;  // ID del partido al que pertenece el candidato

    @NotNull(message = "El ID del candidato es obligatorio")
    private Long idCandidato;  // ID del candidato por el que se vota

    //@NotBlank(message = "El tipo de voto es obligatorio")
    //@Pattern(regexp = "presidente|vicepresidente", 
    //         message = "El tipo de voto debe ser 'presidente' o 'vicepresidente'")
    //private String tipoVoto;  // Tipo de voto (presidente/vicepresidente)
}
