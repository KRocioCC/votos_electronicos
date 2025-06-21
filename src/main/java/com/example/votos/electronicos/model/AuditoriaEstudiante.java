package com.example.votos.electronicos.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "auditoria_estudiante")
public class AuditoriaEstudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accion; // INSERT, UPDATE, DELETE

    private Long idEstudiante;

    private String nombreAnterior;
    private String nombreNuevo;
    private String apellidoPatAnterior;
    private String apellidoPatNuevo;

    private String apellidoMatAnterior;
    private String apellidoMatNuevo;

    private String carreraAnterior;
    private String carreraNueva;

    private String correoAnterior;
    private String correoNuevo;

 
    private LocalDateTime fecha;
}