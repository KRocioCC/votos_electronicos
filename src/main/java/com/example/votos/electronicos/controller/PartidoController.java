package com.example.votos.electronicos.controller;

import com.example.votos.electronicos.dto.PartidoDTO;
import com.example.votos.electronicos.service.IPartidoService;

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
@RequestMapping("/api/partidos")
@Validated
public class PartidoController {

    private final IPartidoService partidoService;
    private static final Logger logger = LoggerFactory.getLogger(PartidoController.class);

    @Autowired
    public PartidoController(IPartidoService partidoService) {
        this.partidoService = partidoService;
    }

    // Obtener todos los partidos
    @GetMapping
    public ResponseEntity<List<PartidoDTO>> obtenerTodosLosPartidos() {
        long inicio = System.currentTimeMillis();
        logger.info("[PARTIDO] Inicio obtenerTodosLosPartidos: {}", inicio);
        List<PartidoDTO> partidos = partidoService.obtenerTodosLosPartidos();
        long fin = System.currentTimeMillis();
        logger.info("[PARTIDO] Fin obtenerTodosLosPartidos: {} (Duración: {} ms)", fin, (fin - inicio));
        return ResponseEntity.ok(partidos);
    }

    // Obtener un partido por su ID
    @GetMapping("/{id}")
    public ResponseEntity<PartidoDTO> obtenerPartidoPorId(@PathVariable Long id) {
        long inicio = System.currentTimeMillis();
        logger.info("[PARTIDO] Inicio obtenerPartidoPorId: {}", inicio);
        PartidoDTO partido = partidoService.obtenerPartidoPorId(id);
        long fin = System.currentTimeMillis();
        logger.info("[PARTIDO] Fin obtenerPartidoPorId: {} (Duración: {} ms)", fin, (fin - inicio));
        return ResponseEntity.ok(partido);
    }

    // Crear un nuevo partido
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PartidoDTO> crearPartido(@Valid @RequestBody PartidoDTO partidoDTO) {
        PartidoDTO creado = partidoService.crearPartido(partidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // Actualizar un partido existente
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PartidoDTO> actualizarPartido(@PathVariable Long id, @RequestBody PartidoDTO partidoDTO) {
        PartidoDTO actualizado = partidoService.actualizarPartido(id, partidoDTO);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar un partido
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarPartido(@PathVariable Long id) {
        partidoService.eliminarPartido(id);
        return ResponseEntity.ok("Partido eliminado correctamente");
    }
}
