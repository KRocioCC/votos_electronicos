package com.example.votos.electronicos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConteoVotosPartidoCandidatoDTO {
    private String nombrePartido;
    private String nombreCandidato;
    private Long totalVotos;
}