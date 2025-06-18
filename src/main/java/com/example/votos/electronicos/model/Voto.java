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
@Table(name = "votos")
@EqualsAndHashCode
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_voto")
    private Long idVoto;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante")
    private Estudiante estudiante;  // Relación con Estudiante (un candidato es un docente, estudiante, etc.)

    @ManyToOne
    @JoinColumn(name = "id_docente", referencedColumnName = "id_docente")
    private Docente docente;  // Relación con Docente (si es el caso)

    @ManyToOne
    @JoinColumn(name = "id_partido", referencedColumnName = "id_partido")
    private Partido partido;  // Relación con Partido (al cual se vota)

    @ManyToOne
    @JoinColumn(name = "id_candidato", referencedColumnName = "id_candidato")
    private Candidato candidato;  // Relación con Candidato (al cual se le da el voto)

    //@Column(name = "tipo_voto", nullable = false)
    //private String tipoVoto;  // Tipo de voto (presidente/vicepresidente)
}
