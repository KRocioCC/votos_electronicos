package com.example.votos.electronicos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import jakarta.validation.constraints.*;

/**
 * DTO para transferencia de datos de Estudiante con validaciones.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstudianteDTO implements Serializable {

    private Long idEstudiante;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido paterno es obligatorio")
    private String apellidoPat;

    @NotBlank(message = "El apellido materno es obligatorio")
    private String apellidoMat;

    @NotBlank(message = "La carrera es obligatoria")
    private String carrera;

    @NotBlank(message = "El correo institucional es obligatorio")
    @Email(message = "El correo debe ser válido")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@umsa\\.bo$", 
             message = "El correo debe ser institucional terminando en @umsa.bo")
    private String correoInstitucional;

    @NotNull(message = "El voto es obligatorio")
    private Boolean voto;  // true si votó, false si no votó
}
