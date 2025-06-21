package com.example.votos.electronicos.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa un voto emitido por un votante a un partido político.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
    name = "votos",
    uniqueConstraints = @UniqueConstraint(columnNames = "id_votante")
)
@EqualsAndHashCode
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_voto")
    private Long idVoto;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_votante", referencedColumnName = "id_votante", nullable = false, unique = true)
    private Votante votante;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_partido", referencedColumnName = "id_partido", nullable = false)
    private Partido partido;
}
