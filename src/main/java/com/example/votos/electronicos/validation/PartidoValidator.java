package com.example.votos.electronicos.validation;

import com.example.votos.electronicos.dto.PartidoDTO;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PartidoValidator {

    private static final Pattern NOMBRE_PARTIDO_PATTERN = Pattern.compile("^[a-zA-Z0-9 ]+$");  // Solo caracteres alfanuméricos y espacios
    private static final Pattern SIGLA_PARTIDO_PATTERN = Pattern.compile("^[a-zA-Z]{2,10}$");  // Siglas de entre 2 y 10 caracteres alfabéticos

    public void validaNombrePartido(String nombre) {
        if (nombre == null || !NOMBRE_PARTIDO_PATTERN.matcher(nombre).matches()) {
            throw new BusinessException("El nombre del partido no es válido. Solo puede contener caracteres alfanuméricos y espacios.");
        }
    }

    public void validaSigla(String sigla) {
        if (sigla == null || !SIGLA_PARTIDO_PATTERN.matcher(sigla).matches()) {
            throw new BusinessException("La sigla del partido no es válida. Debe contener entre 2 y 10 caracteres alfabéticos.");
        }
    }

    public void validacionCompletaPartido(PartidoDTO partidoDTO) {
        validaNombrePartido(partidoDTO.getNombrePartido());
        validaSigla(partidoDTO.getSigla());
        // Puedes agregar más validaciones si lo consideras necesario (por ejemplo, no nulos, longitud, etc.)
    }

    public static class BusinessException extends RuntimeException {
        public BusinessException(String message) {
            super(message);
        }
    }
}
