package com.example.votos.electronicos.repository;

import com.example.votos.electronicos.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    Boolean existsByCorreoInstitucional(String correoInstitucional);

//para login
    Optional<Estudiante> findByCorreoInstitucional(String correoInstitucional);
}
