package com.example.votos.electronicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "votantes")
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Votante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_votante", nullable = false)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido_pat", nullable = false, length = 50)
    private String apellidoPat;

    @Column(name = "apellido_mat", nullable = false, length = 50)
    private String apellidoMat;

    @Column(nullable = false, length = 100)
    private String carrera;

    @Email
    @Column(name = "correo_institucional", nullable = false, unique = true, length = 70)
    private String correoInstitucional;

    @Column(nullable = false)
    private Boolean voto;
}
