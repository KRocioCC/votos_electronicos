package com.example.votos.electronicos.service.impl;

import com.example.votos.electronicos.dto.DocenteDTO;
import com.example.votos.electronicos.model.Docente;
import com.example.votos.electronicos.repository.DocenteRepository;
import com.example.votos.electronicos.service.IDocenteService;
import com.example.votos.electronicos.validation.DocenteValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocenteServiceImpl implements IDocenteService {

    private final DocenteRepository docenteRepository;
    private final DocenteValidator docenteValidator;

    @Autowired
    public DocenteServiceImpl(DocenteRepository docenteRepository, DocenteValidator docenteValidator) {
        this.docenteRepository = docenteRepository;
        this.docenteValidator = docenteValidator;
    }

    @Override
    public List<DocenteDTO> obtenerTodosLosDocentes() {
        return docenteRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DocenteDTO obtenerDocentePorId(Long id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado con ID: " + id));
        return convertToDTO(docente);
    }

    @Override
    @Transactional
    public DocenteDTO crearDocente(DocenteDTO docenteDTO) {
        docenteValidator.validacionCompletaDocente(docenteDTO);

        Docente docente = convertToEntity(docenteDTO);
        Docente docenteGuardado = docenteRepository.save(docente);

        return convertToDTO(docenteGuardado);
    }

    @Override
    @Transactional
    public DocenteDTO actualizarDocente(Long id, DocenteDTO docenteDTO) {
        Docente docenteExistente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado con ID: " + id));

        docenteValidator.validacionCompletaDocente(docenteDTO);

        docenteExistente.setNombre(docenteDTO.getNombre());
        docenteExistente.setApellidoPat(docenteDTO.getApellidoPat());
        docenteExistente.setApellidoMat(docenteDTO.getApellidoMat());
        docenteExistente.setCarrera(docenteDTO.getCarrera());
        docenteExistente.setCorreoInstitucional(docenteDTO.getCorreoInstitucional());
        docenteExistente.setVoto(docenteDTO.getVoto());

        Docente docenteActualizado = docenteRepository.save(docenteExistente);
        return convertToDTO(docenteActualizado);
    }

    @Override
    @Transactional
    public DocenteDTO eliminarDocente(Long id) {
        Docente docenteExistente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado con ID: " + id));

        docenteRepository.delete(docenteExistente);

        return convertToDTO(docenteExistente);
    }

    @Override
    @Transactional(readOnly = false)
    public Docente obtenerDocenteConBloqueo(Long id) {
        Docente docente = docenteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Docente no encontrado con ID: " + id));
        try {
            Thread.sleep(15000); // Simula espera para mostrar bloqueo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return docente;
    }

    @Override
    @Transactional
    public void eliminarDocenteFisicamente(Long id) {
        Docente docenteExistente = docenteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Docente no encontrado con ID: " + id));
        docenteRepository.delete(docenteExistente);
    }

    private DocenteDTO convertToDTO(Docente docente) {
        return DocenteDTO.builder()
                .idDocente(docente.getIdDocente())
                .nombre(docente.getNombre())
                .apellidoPat(docente.getApellidoPat())
                .apellidoMat(docente.getApellidoMat())
                .carrera(docente.getCarrera())
                .correoInstitucional(docente.getCorreoInstitucional())
                .voto(docente.getVoto())
                .build();
    }

    private Docente convertToEntity(DocenteDTO docenteDTO) {
        return Docente.builder()
                .idDocente(docenteDTO.getIdDocente())
                .nombre(docenteDTO.getNombre())
                .apellidoPat(docenteDTO.getApellidoPat())
                .apellidoMat(docenteDTO.getApellidoMat())
                .carrera(docenteDTO.getCarrera())
                .correoInstitucional(docenteDTO.getCorreoInstitucional())
                .voto(docenteDTO.getVoto())
                .build();
    }
}
