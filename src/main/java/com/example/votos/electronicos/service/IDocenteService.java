package com.example.votos.electronicos.service;

import com.example.votos.electronicos.dto.DocenteDTO;
import com.example.votos.electronicos.model.Docente;

import java.util.List;

public interface IDocenteService {

    // Obtener todos los docentes
    List<DocenteDTO> obtenerTodosLosDocentes();

    // Obtener un docente por su ID
    DocenteDTO obtenerDocentePorId(Long id);

    // Crear un nuevo docente
    DocenteDTO crearDocente(DocenteDTO docenteDTO);

    // Actualizar un docente
    DocenteDTO actualizarDocente(Long id, DocenteDTO docenteDTO);

    // Eliminar un docente
    DocenteDTO eliminarDocente(Long id);

    // Método adicional para obtener un docente con bloqueo (si se necesita)
    Docente obtenerDocenteConBloqueo(Long id);

    // Eliminar físicamente un docente (si se necesita)
    void eliminarDocenteFisicamente(Long id);
}
