package com.example.votos.electronicos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import jakarta.validation.constraints.*;

/**
 * DTO para transferencia de datos de Candidato con validaciones.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidatoDTO implements Serializable {

    private Long idCandidato;

    @NotNull(message = "El ID del docente es obligatorio")
    private Long idDocente;  // ID del docente (referencia a Docente)

    @NotNull(message = "El ID del partido es obligatorio")
    private Long idPartido;  // ID del partido (referencia a Partido)

    @NotBlank(message = "El cargo es obligatorio")
    @Pattern(regexp = "decano|vicedecano", 
             message = "El cargo debe ser 'decano' o 'vicedecano'")
    private String cargo;  // Cargo del candidato (decano o vicedecano)
}
