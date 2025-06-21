package com.example.votos.electronicos.repository;

import com.example.votos.electronicos.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {

    Boolean existsByCorreoInstitucional(String correoInstitucional);
    //para login
    Optional<Docente> findByCorreoInstitucional(String correoInstitucional);

    // Ejemplo de consulta personalizada
    Optional<Docente> findById(Long id); // ✅ Este ya está incluido automáticamente
}
