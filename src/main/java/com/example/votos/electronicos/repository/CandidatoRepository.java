package com.example.votos.electronicos.repository;

import com.example.votos.electronicos.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    // Usa los nombres correctos de los campos de las entidades relacionadas
    Boolean existsByDocenteIdDocenteAndPartidoIdPartido(Long idDocente, Long idPartido);
}