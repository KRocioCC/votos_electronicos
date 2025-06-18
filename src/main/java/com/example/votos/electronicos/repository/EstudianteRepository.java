package com.example.votos.electronicos.repository;

import com.example.votos.electronicos.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    Boolean existsByCorreoInstitucional(String correoInstitucional);

    // Puedes agregar más métodos personalizados si lo necesitas
}
