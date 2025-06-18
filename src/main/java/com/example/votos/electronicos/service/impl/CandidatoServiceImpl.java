package com.example.votos.electronicos.service.impl;

import com.example.votos.electronicos.dto.CandidatoDTO;
import com.example.votos.electronicos.model.Candidato;
import com.example.votos.electronicos.model.Docente;
import com.example.votos.electronicos.model.Partido;
import com.example.votos.electronicos.repository.CandidatoRepository;
import com.example.votos.electronicos.service.ICandidatoService;
import com.example.votos.electronicos.validation.CandidatoValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatoServiceImpl implements ICandidatoService {

    private final CandidatoRepository candidatoRepository;
    private final CandidatoValidator candidatoValidator;

    @Autowired
    public CandidatoServiceImpl(CandidatoRepository candidatoRepository, CandidatoValidator candidatoValidator) {
        this.candidatoRepository = candidatoRepository;
        this.candidatoValidator = candidatoValidator;
    }

    @Override
    public List<CandidatoDTO> obtenerTodosLosCandidatos() {
        return candidatoRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CandidatoDTO obtenerCandidatoPorId(Long id) {
        Candidato candidato = candidatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado con ID: " + id));
        return convertToDTO(candidato);
    }

    @Override
    @Transactional
    public CandidatoDTO crearCandidato(CandidatoDTO candidatoDTO) {
        candidatoValidator.validacionCompletaCandidato(candidatoDTO);

        Candidato candidato = convertToEntity(candidatoDTO);
        Candidato candidatoGuardado = candidatoRepository.save(candidato);

        return convertToDTO(candidatoGuardado);
    }

    @Override
    @Transactional
    public CandidatoDTO actualizarCandidato(Long id, CandidatoDTO candidatoDTO) {
        Candidato candidatoExistente = candidatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado con ID: " + id));

        candidatoValidator.validacionCompletaCandidato(candidatoDTO);

        candidatoExistente.setDocente(new Docente(candidatoDTO.getIdDocente()));
        candidatoExistente.setPartido(new Partido(candidatoDTO.getIdPartido()));
        candidatoExistente.setCargo(candidatoDTO.getCargo());

        Candidato candidatoActualizado = candidatoRepository.save(candidatoExistente);
        return convertToDTO(candidatoActualizado);
    }



    @Override
    @Transactional
    public void eliminarCandidatoFisicamente(Long id) {
        Candidato candidatoExistente = candidatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado con ID: " + id));
        candidatoRepository.delete(candidatoExistente);
    }

    private CandidatoDTO convertToDTO(Candidato candidato) {
        return CandidatoDTO.builder()
                .idCandidato(candidato.getIdCandidato())
                .idDocente(candidato.getDocente().getIdDocente())
                .idPartido(candidato.getPartido().getIdPartido())
                .cargo(candidato.getCargo())
                .build();
    }

    private Candidato convertToEntity(CandidatoDTO candidatoDTO) {
        return Candidato.builder()
                .idCandidato(candidatoDTO.getIdCandidato())
                .docente(new Docente(candidatoDTO.getIdDocente()))
                .partido(new Partido(candidatoDTO.getIdPartido()))
                .cargo(candidatoDTO.getCargo())
                .build();
    }
}