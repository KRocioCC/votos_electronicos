package com.example.votos.electronicos.service;

import com.example.votos.electronicos.dto.PartidoDTO;
import com.example.votos.electronicos.model.Partido;

import java.util.List;

public interface IPartidoService {

    // Obtener todos los partidos
    List<PartidoDTO> obtenerTodosLosPartidos();

    // Obtener un partido por su ID
    PartidoDTO obtenerPartidoPorId(Long id);

    // Crear un nuevo partido
    PartidoDTO crearPartido(PartidoDTO partidoDTO);

    // Actualizar un partido
    PartidoDTO actualizarPartido(Long id, PartidoDTO partidoDTO);

    // Eliminar un partido
    PartidoDTO eliminarPartido(Long id);

 

    // Eliminar físicamente un partido (si se necesita)
    void eliminarPartidoFisicamente(Long id);
}
