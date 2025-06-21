package com.example.votos.electronicos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "estudiantes")
@EqualsAndHashCode(callSuper = true)
public class Estudiante extends Votante {

    public Estudiante(Long id) {
        super.setId(id);
    }

    @Column(name = "anio_ingreso", nullable = false)
    private Integer anioIngreso;
}
