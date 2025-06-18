package com.example.votos.electronicos.controller;

import com.example.votos.electronicos.model.AuditoriaEstudiante;
import com.example.votos.electronicos.repository.AuditoriaEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auditoria-estudiantes")
public class AuditoriaEstudianteController {

    @Autowired
    private AuditoriaEstudianteRepository auditoriaRepo;

    @GetMapping
    public List<AuditoriaEstudiante> getAll() {
        return auditoriaRepo.findAll();
    }

    @GetMapping("/estudiante/{id}")
    public List<AuditoriaEstudiante> getByEstudiante(@PathVariable Long id) {
        return auditoriaRepo.findByIdEstudiante(id);
    }
}