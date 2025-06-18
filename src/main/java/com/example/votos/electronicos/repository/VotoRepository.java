package com.example.votos.electronicos.repository;

import com.example.votos.electronicos.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    // Verifica si ya existe un voto de un Estudiante o Docente para un Candidato y Partido
    Boolean existsByEstudianteIdEstudianteAndPartidoIdPartidoAndCandidatoIdCandidato(Long idEstudiante, Long idPartido, Long idCandidato);

    //agregando la funcion para contar votos por candidato
    @Query(value = "SELECT * FROM contar_votos_candidatos()", nativeQuery = true)
    List<Object[]> contarVotosPorCandidatoRaw();

    @Query(value = "SELECT * FROM contar_votos_por_partido_y_candidato()", nativeQuery = true)
    List<Object[]> contarVotosPorPartidoYCandidatoRaw();
}
