package com.example.votos.electronicos.repository;

import com.example.votos.electronicos.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    // Verifica si ya existe un voto del votante hacia un partido

    Boolean existsByVotanteIdAndPartidoIdPartido(Long idVotante, Long idPartido);

    //FUNCION CARRERAS Y VOTOS
    @Query(value = "SELECT * FROM top_carreras_con_mas_votos()", nativeQuery = true)
    List<Object[]> contarVotosPorCarreraRaw();


    //FUNCION DASHBOARD
    @Query(value = "SELECT * FROM contar_votos_por_partido()", nativeQuery = true)
    List<Object[]> contarVotosPorPartidoRaw();

}
