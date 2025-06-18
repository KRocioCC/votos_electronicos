package com.example.votos.electronicos.repository;

import com.example.votos.electronicos.model.AuditoriaEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditoriaEstudianteRepository extends JpaRepository<AuditoriaEstudiante, Long> {
    List<AuditoriaEstudiante> findByIdEstudiante(Long idEstudiante);
}