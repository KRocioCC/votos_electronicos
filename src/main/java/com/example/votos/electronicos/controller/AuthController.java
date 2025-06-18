package com.example.votos.electronicos.controller;

import com.example.votos.electronicos.model.Estudiante;
import com.example.votos.electronicos.model.Docente;
import com.example.votos.electronicos.model.Admin;
import com.example.votos.electronicos.repository.EstudianteRepository;
import com.example.votos.electronicos.repository.DocenteRepository;
import com.example.votos.electronicos.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private DocenteRepository docenteRepository;
    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String correo = body.get("correo");
        String password = body.get("password"); // Solo para admin

        // Intentar login como admin
        Optional<Admin> admin = adminRepository.findByCorreo(correo);
        if (admin.isPresent() && admin.get().getPassword().equals(password)) {
            return ResponseEntity.ok(Map.of(
                "id", admin.get().getIdAdmin(),
                "tipo", "admin"
            ));
        }

        // Login como estudiante
        Optional<Estudiante> estudiante = estudianteRepository.findByCorreoInstitucional(correo);
        if (estudiante.isPresent()) {
            return ResponseEntity.ok(Map.of(
                "id", estudiante.get().getIdEstudiante(),
                "tipo", "estudiante"
            ));
        }

        // Login como docente
        Optional<Docente> docente = docenteRepository.findByCorreoInstitucional(correo);
        if (docente.isPresent()) {
            return ResponseEntity.ok(Map.of(
                "id", docente.get().getIdDocente(),
                "tipo", "docente"
            ));
        }

        return ResponseEntity.status(401).body("Correo no registrado");
    }
}