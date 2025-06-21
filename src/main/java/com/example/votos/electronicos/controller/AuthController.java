package com.example.votos.electronicos.controller;

import com.example.votos.electronicos.model.Admin;
import com.example.votos.electronicos.model.Votante;
import com.example.votos.electronicos.model.Docente;
import com.example.votos.electronicos.model.Estudiante;
import com.example.votos.electronicos.repository.AdminRepository;
import com.example.votos.electronicos.repository.VotanteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private VotanteRepository votanteRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String correo = body.get("correo");
        String password = body.get("password"); // Solo se usa para Admin

        // Intento como Admin
        Optional<Admin> admin = adminRepository.findByCorreo(correo);
        if (admin.isPresent() && admin.get().getPassword().equals(password)) {
            return ResponseEntity.ok(Map.of(
                "id", admin.get().getIdAdmin(),
                "tipo", "admin"
            ));
        }

        // Intento como Votante (Docente o Estudiante)
        Optional<Votante> votante = votanteRepository.findByCorreoInstitucional(correo);
        if (votante.isPresent()) {
            Votante v = votante.get();
            String tipo = (v instanceof Docente) ? "docente" : "estudiante";
            return ResponseEntity.ok(Map.of(
                "id", v.getId(),
                "tipo", tipo
            ));
        }

        return ResponseEntity.status(401).body(Map.of(
            "mensaje", "Correo no registrado"
        ));
    }
}
