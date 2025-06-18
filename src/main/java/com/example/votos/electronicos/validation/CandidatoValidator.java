package com.example.votos.electronicos.validation;

import com.example.votos.electronicos.dto.CandidatoDTO;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class CandidatoValidator {

    private static final Pattern CARGO_PATTERN = Pattern.compile("presidente|vicepresidente");

    public void validaCargo(String cargo) {
        if (cargo == null || !CARGO_PATTERN.matcher(cargo).matches()) {
            throw new BusinessException("El cargo debe ser 'presidente' o 'vicepresidente'");
        }
    }

    public void validacionCompletaCandidato(CandidatoDTO candidatoDTO) {
        validaCargo(candidatoDTO.getCargo());
        // Puedes agregar más validaciones si lo consideras necesario (por ejemplo, no nulos, longitud, etc.)
    }

    public static class BusinessException extends RuntimeException {
        public BusinessException(String message) {
            super(message);
        }
    }
}
