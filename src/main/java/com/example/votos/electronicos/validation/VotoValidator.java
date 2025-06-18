package com.example.votos.electronicos.validation;

import com.example.votos.electronicos.dto.VotoDTO;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class VotoValidator {

    private static final Pattern TIPO_VOTO_PATTERN = Pattern.compile("presidente|vicepresidente");

    public void validaTipoVoto(String tipoVoto) {
        if (tipoVoto == null || !TIPO_VOTO_PATTERN.matcher(tipoVoto).matches()) {
            throw new BusinessException("El tipo de voto debe ser 'presidente' o 'vicepresidente'");
        }
    }

    public void validacionCompletaVoto(VotoDTO votoDTO) {
        // Puedes agregar más validaciones si lo consideras necesario (por ejemplo, no nulos, longitud, etc.)
    }

    public static class BusinessException extends RuntimeException {
        public BusinessException(String message) {
            super(message);
        }
    }
}
