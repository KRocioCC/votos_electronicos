package com.example.votos.electronicos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * DTO para transferencia de datos de Voto con validaciones.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VotoDTO implements Serializable {

    private Long idVoto;

    @NotNull(message = "El ID del votante es obligatorio")
    private Long idVotante;  // Estudiante o docente

    @NotNull(message = "El ID del partido es obligatorio")
    private Long idPartido;
}
