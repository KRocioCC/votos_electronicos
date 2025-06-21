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
@Table(
    name = "candidatos",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_partido", "cargo"})  // Un cargo por partido
    }
)
@EqualsAndHashCode
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidato")
    private Long idCandidato;

    //CAMBIO
    @ManyToOne
    @JoinColumn(name = "id_votante", nullable = false)
    private Docente docente;


    @ManyToOne(optional = false)
    @JoinColumn(name = "id_partido", referencedColumnName = "id_partido", nullable = false)
    private Partido partido;  // Pertenece a un partido

    @Column(name = "cargo", nullable = false, length = 50)
    private String cargo;  // "decano" o "vicedecano" por ejemplo

    // Constructor auxiliar por ID
    public Candidato(Long idCandidato) {
        this.idCandidato = idCandidato;
    }
}
