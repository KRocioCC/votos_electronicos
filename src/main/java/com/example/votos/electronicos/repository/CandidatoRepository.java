package com.example.votos.electronicos.repository;

import com.example.votos.electronicos.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    // Verifica si un docente ya está registrado como candidato en un partido
    

    // ✅ Correcto
    Boolean existsByDocenteIdAndPartidoIdPartido(Long idDocente, Long idPartido);


    // Verifica si ya existe un cargo ocupado en ese partido
    Boolean existsByPartidoIdPartidoAndCargo(Long idPartido, String cargo);
}
