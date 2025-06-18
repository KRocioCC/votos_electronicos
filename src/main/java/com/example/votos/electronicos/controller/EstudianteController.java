package com.example.votos.electronicos.controller;

import com.example.votos.electronicos.dto.EstudianteDTO;
import com.example.votos.electronicos.service.IEstudianteService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@Validated
public class EstudianteController {

    private final IEstudianteService estudianteService;
    private static final Logger logger = LoggerFactory.getLogger(EstudianteController.class);

    @Autowired
    public EstudianteController(IEstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // Obtener todos los estudiantes
    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> obtenerTodosLosEstudiantes() {
        long inicio = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Inicio obtenerTodosLosEstudiantes: {}", inicio);
        List<EstudianteDTO> estudiantes = estudianteService.obtenerTodosLosEstudiantes();
        long fin = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Fin obtenerTodosLosEstudiantes: {} (Duración: {} ms)", fin, (fin - inicio));
        return ResponseEntity.ok(estudiantes);
    }

    // Obtener un estudiante por su ID
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> obtenerEstudiantePorId(@PathVariable Long id) {
        long inicio = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Inicio obtenerEstudiantePorId: {}", inicio);
        EstudianteDTO estudiante = estudianteService.obtenerEstudiantePorId(id);
        long fin = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Fin obtenerEstudiantePorId: {} (Duración: {} ms)", fin, (fin - inicio));
        return ResponseEntity.ok(estudiante);
    }

    // Crear un nuevo estudiante
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EstudianteDTO> crearEstudiante(@Valid @RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO creado = estudianteService.crearEstudiante(estudianteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // Actualizar un estudiante existente
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(@PathVariable Long id, @RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO actualizado = estudianteService.actualizarEstudiante(id, estudianteDTO);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar un estudiante (eliminación lógica)
    @PutMapping("/{id}/eliminar")
    @Transactional
    public ResponseEntity<EstudianteDTO> eliminarEstudiante(@PathVariable Long id) {
        EstudianteDTO eliminado = estudianteService.eliminarEstudiante(id);
        return ResponseEntity.ok(eliminado);
    }

    // Eliminar un estudiante físicamente de la base de datos
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarEstudianteFisicamente(@PathVariable Long id) {
        estudianteService.eliminarEstudianteFisicamente(id);
        return ResponseEntity.ok("Estudiante eliminado correctamente");
    }
}
