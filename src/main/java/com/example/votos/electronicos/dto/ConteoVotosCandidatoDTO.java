package com.example.votos.electronicos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConteoVotosCandidatoDTO {
    private Long candidatoId;
    private String candidatoNombre;
    private Long totalVotos;
}