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
@Table(name = "candidatos")
@EqualsAndHashCode
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidato")
    private Long idCandidato;

    @ManyToOne
    @JoinColumn(name = "id_docente", referencedColumnName = "id_docente", nullable = false)
    private Docente docente;  // Relación con la entidad Docente (un candidato es un docente)

    @ManyToOne
    @JoinColumn(name = "id_partido", referencedColumnName = "id_partido", nullable = false)
    private Partido partido;  // Relación con la entidad Partido (un candidato pertenece a un partido)

    @Column(name = "cargo", nullable = false)
    private String cargo;  // Cargo del candidato (presidente, vicepresidente)


    // Constructor solo con idPartido
    public Candidato(Long idCandidato) {
        this.idCandidato = idCandidato;
    }
}
