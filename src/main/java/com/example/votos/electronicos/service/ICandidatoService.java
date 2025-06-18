package com.example.votos.electronicos.service;

import com.example.votos.electronicos.dto.CandidatoDTO;
import com.example.votos.electronicos.model.Candidato;

import java.util.List;

public interface ICandidatoService {

    // Obtener todos los candidatos
    List<CandidatoDTO> obtenerTodosLosCandidatos();

    // Obtener un candidato por su ID
    CandidatoDTO obtenerCandidatoPorId(Long id);

    // Crear un nuevo candidato
    CandidatoDTO crearCandidato(CandidatoDTO candidatoDTO);

    // Actualizar un candidato
    CandidatoDTO actualizarCandidato(Long id, CandidatoDTO candidatoDTO);

  

    // Eliminar físicamente un candidato
    void eliminarCandidatoFisicamente(Long id);
}
