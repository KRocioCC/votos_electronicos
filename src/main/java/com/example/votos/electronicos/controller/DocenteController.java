package com.example.votos.electronicos.controller;

import com.example.votos.electronicos.dto.DocenteDTO;
import com.example.votos.electronicos.service.IDocenteService;

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
@RequestMapping("/api/docentes")
@Validated
public class DocenteController {

    private final IDocenteService docenteService;
    private static final Logger logger = LoggerFactory.getLogger(DocenteController.class);

    @Autowired
    public DocenteController(IDocenteService docenteService) {
        this.docenteService = docenteService;
    }

    // Obtener todos los docentes
    @GetMapping
    public ResponseEntity<List<DocenteDTO>> obtenerTodosLosDocentes() {
        long inicio = System.currentTimeMillis();
        logger.info("[DOCENTE] Inicio obtenerTodosLosDocentes: {}", inicio);
        List<DocenteDTO> docentes = docenteService.obtenerTodosLosDocentes();
        long fin = System.currentTimeMillis();
        logger.info("[DOCENTE] Fin obtenerTodosLosDocentes: {} (Duración: {} ms)", fin, (fin - inicio));
        return ResponseEntity.ok(docentes);
    }

    // Obtener un docente por su ID
    @GetMapping("/{id}")
    public ResponseEntity<DocenteDTO> obtenerDocentePorId(@PathVariable Long id) {
        long inicio = System.currentTimeMillis();
        logger.info("[DOCENTE] Inicio obtenerDocentePorId: {}", inicio);
        DocenteDTO docente = docenteService.obtenerDocentePorId(id);
        long fin = System.currentTimeMillis();
        logger.info("[DOCENTE] Fin obtenerDocentePorId: {} (Duración: {} ms)", fin, (fin - inicio));
        return ResponseEntity.ok(docente);
    }

    // Crear un nuevo docente
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DocenteDTO> crearDocente(@Valid @RequestBody DocenteDTO docenteDTO) {
        DocenteDTO creado = docenteService.crearDocente(docenteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // Actualizar un docente existente
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DocenteDTO> actualizarDocente(@PathVariable Long id, @RequestBody DocenteDTO docenteDTO) {
        DocenteDTO actualizado = docenteService.actualizarDocente(id, docenteDTO);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar un docente (eliminación lógica)
    @PutMapping("/{id}/eliminar")
    @Transactional
    public ResponseEntity<DocenteDTO> eliminarDocente(@PathVariable Long id) {
        DocenteDTO eliminado = docenteService.eliminarDocente(id);
        return ResponseEntity.ok(eliminado);
    }

    // Eliminar un docente físicamente de la base de datos
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarDocenteFisicamente(@PathVariable Long id) {
        docenteService.eliminarDocenteFisicamente(id);
        return ResponseEntity.ok("Docente eliminado correctamente");
    }
}
