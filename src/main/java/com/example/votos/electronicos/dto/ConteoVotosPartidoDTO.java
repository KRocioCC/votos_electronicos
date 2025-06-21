package com.example.votos.electronicos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConteoVotosPartidoDTO {
    private String nombrePartido;
    private Long totalVotos;
}