package com.example.votos.electronicos.controller;

import com.example.votos.electronicos.dto.VotoDTO;
import com.example.votos.electronicos.service.IVotoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.votos.electronicos.dto.CarreraVotoDTO;

import java.util.List;
import com.example.votos.electronicos.dto.ConteoVotosPartidoDTO;

@RestController
@RequestMapping("/api/votos")
@Validated
public class VotoController {

    private final IVotoService votoService;
    private static final Logger logger = LoggerFactory.getLogger(VotoController.class);

    @Autowired
    public VotoController(IVotoService votoService) {
        this.votoService = votoService;
    }

    // Obtener todos los votos
    @GetMapping
    public ResponseEntity<List<VotoDTO>> obtenerTodosLosVotos() {
        long inicio = System.currentTimeMillis();
        logger.info("[VOTO] Inicio obtenerTodosLosVotos: {}", inicio);
        List<VotoDTO> votos = votoService.obtenerTodosLosVotos();
        long fin = System.currentTimeMillis();
        logger.info("[VOTO] Fin obtenerTodosLosVotos: {} (Duración: {} ms)", fin, (fin - inicio));
        return ResponseEntity.ok(votos);
    }

    // Obtener un voto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<VotoDTO> obtenerVotoPorId(@PathVariable Long id) {
        long inicio = System.currentTimeMillis();
        logger.info("[VOTO] Inicio obtenerVotoPorId: {}", inicio);
        VotoDTO voto = votoService.obtenerVotoPorId(id);
        long fin = System.currentTimeMillis();
        logger.info("[VOTO] Fin obtenerVotoPorId: {} (Duración: {} ms)", fin, (fin - inicio));
        return ResponseEntity.ok(voto);
    }

    // Crear un nuevo voto
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VotoDTO> crearVoto(@Valid @RequestBody VotoDTO votoDTO) {
        VotoDTO creado = votoService.crearVoto(votoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // Eliminar un voto (eliminación lógica) - No permitido, solo en el backend
    @DeleteMapping("/{id}/fisico")
    @Transactional
    public ResponseEntity<String> eliminarVotoFisicamente(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No se pueden eliminar los votos");
    }

    @GetMapping("/votos/por-partido")
    public List<ConteoVotosPartidoDTO> contarVotosPorPartido() {
        return votoService.contarVotosPorPartido();
    }

    @GetMapping("/votos/top-carreras")
    public List<CarreraVotoDTO> contarVotosPorCarrera() {
        return votoService.contarVotosPorCarrera();
    }


}
