package com.example.votos.electronicos.repository;

import com.example.votos.electronicos.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {

    // Puedes agregar más métodos personalizados si lo necesitas
}
