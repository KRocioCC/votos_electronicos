package com.example.votos.electronicos.controller;

import com.example.votos.electronicos.dto.CandidatoDTO;
import com.example.votos.electronicos.service.ICandidatoService;

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
@RequestMapping("/api/candidatos")
@Validated
public class CandidatoController {

    private final ICandidatoService candidatoService;
    private static final Logger logger = LoggerFactory.getLogger(CandidatoController.class);

    @Autowired
    public CandidatoController(ICandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    // Obtener todos los candidatos
    @GetMapping
    public ResponseEntity<List<CandidatoDTO>> obtenerTodosLosCandidatos() {
        long inicio = System.currentTimeMillis();
        logger.info("[CANDIDATO] Inicio obtenerTodosLosCandidatos: {}", inicio);
        List<CandidatoDTO> candidatos = candidatoService.obtenerTodosLosCandidatos();
        long fin = System.currentTimeMillis();
        logger.info("[CANDIDATO] Fin obtenerTodosLosCandidatos: {} (Duración: {} ms)", fin, (fin - inicio));
        return ResponseEntity.ok(candidatos);
    }

    // Obtener un candidato por su ID
    @GetMapping("/{id}")
    public ResponseEntity<CandidatoDTO> obtenerCandidatoPorId(@PathVariable Long id) {
        long inicio = System.currentTimeMillis();
        logger.info("[CANDIDATO] Inicio obtenerCandidatoPorId: {}", inicio);
        CandidatoDTO candidato = candidatoService.obtenerCandidatoPorId(id);
        long fin = System.currentTimeMillis();
        logger.info("[CANDIDATO] Fin obtenerCandidatoPorId: {} (Duración: {} ms)", fin, (fin - inicio));
        return ResponseEntity.ok(candidato);
    }

    // Crear un nuevo candidato
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CandidatoDTO> crearCandidato(@Valid @RequestBody CandidatoDTO candidatoDTO) {
        CandidatoDTO creado = candidatoService.crearCandidato(candidatoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // Actualizar un candidato existente
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CandidatoDTO> actualizarCandidato(@PathVariable Long id, @RequestBody CandidatoDTO candidatoDTO) {
        CandidatoDTO actualizado = candidatoService.actualizarCandidato(id, candidatoDTO);
        return ResponseEntity.ok(actualizado);
    }



    // Eliminar un candidato físicamente de la base de datos
    @DeleteMapping("/{id}/fisico")
    @Transactional
    public ResponseEntity<String> eliminarCandidatoFisicamente(@PathVariable Long id) {
        candidatoService.eliminarCandidatoFisicamente(id);
        return ResponseEntity.ok("Candidato eliminado físicamente correctamente");
    }
}
