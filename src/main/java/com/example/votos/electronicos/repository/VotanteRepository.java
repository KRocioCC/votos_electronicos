package com.example.votos.electronicos.repository;

import com.example.votos.electronicos.model.Votante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VotanteRepository extends JpaRepository<Votante, Long> {
    // Ejemplo útil opcional: verificar si ya votó
    boolean existsByIdAndVotoIsNotNull(Long id);

    Optional<Votante> findByCorreoInstitucional(String correoInstitucional);

}
