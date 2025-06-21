package com.example.votos.electronicos.validation;

import com.example.votos.electronicos.dto.CandidatoDTO;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class CandidatoValidator {

    // Solo "decano" y "vicedecano" están permitidos
    private static final Pattern CARGO_PATTERN = Pattern.compile("decano|vicedecano", Pattern.CASE_INSENSITIVE);

    public void validaCargo(String cargo) {
        if (cargo == null || !CARGO_PATTERN.matcher(cargo).matches()) {
            throw new BusinessException("El cargo debe ser 'decano' o 'vicedecano'");
        }
    }

    public void validacionCompletaCandidato(CandidatoDTO candidatoDTO) {
        validaCargo(candidatoDTO.getCargo());

        if (candidatoDTO.getIdDocente() == null) {
            throw new BusinessException("El ID del docente es obligatorio");
        }

        if (candidatoDTO.getIdPartido() == null) {
            throw new BusinessException("El ID del partido es obligatorio");
        }
    }

    public static class BusinessException extends RuntimeException {
        public BusinessException(String message) {
            super(message);
        }
    }
}
