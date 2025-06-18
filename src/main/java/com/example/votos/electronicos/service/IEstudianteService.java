package com.example.votos.electronicos.service;

import com.example.votos.electronicos.dto.EstudianteDTO;
import com.example.votos.electronicos.model.Estudiante;

import java.util.List;

public interface IEstudianteService {

    // Obtener todos los estudiantes
    List<EstudianteDTO> obtenerTodosLosEstudiantes();

    // Obtener un estudiante por su ID
    EstudianteDTO obtenerEstudiantePorId(Long id);

    // Crear un nuevo estudiante
    EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO);

    // Actualizar un estudiante
    EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO);

    // Eliminar un estudiante
    EstudianteDTO eliminarEstudiante(Long id);

    // Método adicional para obtener un estudiante con bloqueo (si se necesita)
    Estudiante obtenerEstudianteConBloqueo(Long id);

    // Eliminar físicamente un estudiante (si se necesita)
    void eliminarEstudianteFisicamente(Long id);
}
