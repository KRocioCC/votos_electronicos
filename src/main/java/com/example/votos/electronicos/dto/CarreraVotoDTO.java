package com.example.votos.electronicos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarreraVotoDTO {
    private String carrera;
    private Long totalVotos;
}
