package com.example.votos.electronicos.validation;

import com.example.votos.electronicos.dto.EstudianteDTO;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EstudianteValidator {

    private static final Pattern CORREO_INSTITUCIONAL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@umsa\\.bo$");

    public void validaCorreoInstitucional(String correo) {
        if (correo == null || !CORREO_INSTITUCIONAL_PATTERN.matcher(correo).matches()) {
            throw new BusinessException("Correo institucional no válido. Debe terminar en @umsa.bo");
        }
    }

    public void validacionCompletaEstudiante(EstudianteDTO estudianteDTO) {
        validaCorreoInstitucional(estudianteDTO.getCorreoInstitucional());
        // Puedes agregar más validaciones si lo consideras necesario (por ejemplo, no nulos, longitud, etc.)
    }

    public static class BusinessException extends RuntimeException {
        public BusinessException(String message) {
            super(message);
        }
    }
}
