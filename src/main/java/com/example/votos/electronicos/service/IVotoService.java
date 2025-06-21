package com.example.votos.electronicos.service;

import com.example.votos.electronicos.dto.VotoDTO;
import com.example.votos.electronicos.model.Voto;
import com.example.votos.electronicos.dto.CarreraVotoDTO;
import com.example.votos.electronicos.dto.ConteoVotosPartidoDTO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public interface IVotoService {

    // Obtener todos los votos
    List<VotoDTO> obtenerTodosLosVotos();

    // Obtener un voto por su ID
    VotoDTO obtenerVotoPorId(Long id);

    // Crear un nuevo voto
    VotoDTO crearVoto(VotoDTO votoDTO);

    // Actualizar un voto
    VotoDTO actualizarVoto(Long id, VotoDTO votoDTO);

    // Eliminar un voto (eliminación lógica)
    VotoDTO eliminarVoto(Long id);

    // Eliminar físicamente un voto
    void eliminarVotoFisicamente(Long id);

    //FUNCION CARRERAS
    
    // FUNCIÓN CARRERAS
    List<CarreraVotoDTO> contarVotosPorCarrera();


    //FUNCION DASHBOARD
    List<ConteoVotosPartidoDTO> contarVotosPorPartido();

}
