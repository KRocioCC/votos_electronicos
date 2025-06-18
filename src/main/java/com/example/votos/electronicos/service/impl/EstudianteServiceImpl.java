package com.example.votos.electronicos.service.impl;

import com.example.votos.electronicos.dto.EstudianteDTO;
import com.example.votos.electronicos.model.Estudiante;
import com.example.votos.electronicos.repository.EstudianteRepository;
import com.example.votos.electronicos.service.IEstudianteService;
import com.example.votos.electronicos.validation.EstudianteValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final EstudianteValidator estudianteValidator;

    @Autowired
    public EstudianteServiceImpl(EstudianteRepository estudianteRepository, EstudianteValidator estudianteValidator) {
        this.estudianteRepository = estudianteRepository;
        this.estudianteValidator = estudianteValidator;
    }

    @Override
    public List<EstudianteDTO> obtenerTodosLosEstudiantes() {
        return estudianteRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EstudianteDTO obtenerEstudiantePorId(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
        return convertToDTO(estudiante);
    }

    @Override
    @Transactional
    public EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO) {
        estudianteValidator.validacionCompletaEstudiante(estudianteDTO);

        Estudiante estudiante = convertToEntity(estudianteDTO);
        Estudiante estudianteGuardado = estudianteRepository.save(estudiante);

        return convertToDTO(estudianteGuardado);
    }

    @Override
    @Transactional
    public EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO) {
        Estudiante estudianteExistente = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));

        estudianteValidator.validacionCompletaEstudiante(estudianteDTO);

        estudianteExistente.setNombre(estudianteDTO.getNombre());
        estudianteExistente.setApellidoPat(estudianteDTO.getApellidoPat());
        estudianteExistente.setApellidoMat(estudianteDTO.getApellidoMat());
        estudianteExistente.setCarrera(estudianteDTO.getCarrera());
        estudianteExistente.setCorreoInstitucional(estudianteDTO.getCorreoInstitucional());
        estudianteExistente.setVoto(estudianteDTO.getVoto());

        Estudiante estudianteActualizado = estudianteRepository.save(estudianteExistente);
        return convertToDTO(estudianteActualizado);
    }

    @Override
    @Transactional
    public EstudianteDTO eliminarEstudiante(Long id) {
        Estudiante estudianteExistente = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));

        estudianteRepository.delete(estudianteExistente);

        return convertToDTO(estudianteExistente);
    }

    @Override
    @Transactional(readOnly = false)
    public Estudiante obtenerEstudianteConBloqueo(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
        try {
            Thread.sleep(15000); // Simula espera para mostrar bloqueo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return estudiante;
    }

    @Override
    @Transactional
    public void eliminarEstudianteFisicamente(Long id) {
        Estudiante estudianteExistente = estudianteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
        estudianteRepository.delete(estudianteExistente);
    }

    private EstudianteDTO convertToDTO(Estudiante estudiante) {
        return EstudianteDTO.builder()
                .idEstudiante(estudiante.getIdEstudiante())
                .nombre(estudiante.getNombre())
                .apellidoPat(estudiante.getApellidoPat())
                .apellidoMat(estudiante.getApellidoMat())
                .carrera(estudiante.getCarrera())
                .correoInstitucional(estudiante.getCorreoInstitucional())
                .voto(estudiante.getVoto())
                .build();
    }

    private Estudiante convertToEntity(EstudianteDTO estudianteDTO) {
        return Estudiante.builder()
                .idEstudiante(estudianteDTO.getIdEstudiante())
                .nombre(estudianteDTO.getNombre())
                .apellidoPat(estudianteDTO.getApellidoPat())
                .apellidoMat(estudianteDTO.getApellidoMat())
                .carrera(estudianteDTO.getCarrera())
                .correoInstitucional(estudianteDTO.getCorreoInstitucional())
                .voto(estudianteDTO.getVoto())
                .build();
    }
}
