package com.example.votos.electronicos.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "docentes")
@EqualsAndHashCode
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_docente")
    private Long idDocente;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido_pat", nullable = false)
    private String apellidoPat;

    @Column(name = "apellido_mat", nullable = false)
    private String apellidoMat;

    @Column(name = "carrera", nullable = false)
    private String carrera;

    @Column(name = "correo_institucional", nullable = false, unique = true)
    private String correoInstitucional;

    @Column(name = "voto", nullable = false)
    private Boolean voto;  // true si votó, false si no votó

     // Constructor solo con idDocente
    public Docente(Long idDocente) {
        this.idDocente = idDocente;
    }
}
