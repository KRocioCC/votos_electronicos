package com.example.votos.electronicos.validation;

import com.example.votos.electronicos.dto.VotoDTO;
import org.springframework.stereotype.Component;

@Component
public class VotoValidator {

    public void validacionCompletaVoto(VotoDTO votoDTO) {
        if (votoDTO.getIdVotante() == null) {
            throw new BusinessException("El ID del votante es obligatorio");
        }
        if (votoDTO.getIdPartido() == null) {
            throw new BusinessException("El ID del partido es obligatorio");
        }
    }

    public static class BusinessException extends RuntimeException {
        public BusinessException(String message) {
            super(message);
        }
    }
}
