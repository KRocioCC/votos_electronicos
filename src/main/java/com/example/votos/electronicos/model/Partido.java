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
@Table(name = "partidos")
@EqualsAndHashCode
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partido")
    private Long idPartido;

    @Column(name = "nombre_partido", nullable = false)
    private String nombrePartido;

    @Column(name = "sigla", nullable = false, length = 10)
    private String sigla;

    @Column(name = "lema", nullable = true, length = 255)
    private String lema;

    // Constructor solo con idPartido
    public Partido(Long idPartido) {
        this.idPartido = idPartido;
    }
}
