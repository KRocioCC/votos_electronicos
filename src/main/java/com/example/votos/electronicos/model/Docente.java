package com.example.votos.electronicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
@Table(name = "docentes")
@EqualsAndHashCode(callSuper = true)

public class Docente extends Votante {

    public Docente(Long id) {
        super.setId(id);
    }

    @Column(name = "antiguedad", nullable = false, length = 50)
    private Integer antiguedad; // Años de experiencia o permanencia del docente
}
