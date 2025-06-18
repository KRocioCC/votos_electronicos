package com.example.votos.electronicos.service.impl;

import com.example.votos.electronicos.dto.PartidoDTO;
import com.example.votos.electronicos.model.Partido;
import com.example.votos.electronicos.repository.PartidoRepository;
import com.example.votos.electronicos.service.IPartidoService;
import com.example.votos.electronicos.validation.PartidoValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartidoServiceImpl implements IPartidoService {

    private final PartidoRepository partidoRepository;
    private final PartidoValidator partidoValidator;

    @Autowired
    public PartidoServiceImpl(PartidoRepository partidoRepository, PartidoValidator partidoValidator) {
        this.partidoRepository = partidoRepository;
        this.partidoValidator = partidoValidator;
    }

    @Override
    public List<PartidoDTO> obtenerTodosLosPartidos() {
        return partidoRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PartidoDTO obtenerPartidoPorId(Long id) {
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con ID: " + id));
        return convertToDTO(partido);
    }

    @Override
    @Transactional
    public PartidoDTO crearPartido(PartidoDTO partidoDTO) {
        partidoValidator.validacionCompletaPartido(partidoDTO);

        Partido partido = convertToEntity(partidoDTO);
        Partido partidoGuardado = partidoRepository.save(partido);

        return convertToDTO(partidoGuardado);
    }

    @Override
    @Transactional
    public PartidoDTO actualizarPartido(Long id, PartidoDTO partidoDTO) {
        Partido partidoExistente = partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con ID: " + id));

        partidoValidator.validacionCompletaPartido(partidoDTO);

        partidoExistente.setNombrePartido(partidoDTO.getNombrePartido());
        partidoExistente.setSigla(partidoDTO.getSigla());
        partidoExistente.setLema(partidoDTO.getLema());

        Partido partidoActualizado = partidoRepository.save(partidoExistente);
        return convertToDTO(partidoActualizado);
    }

    @Override
    @Transactional
    public PartidoDTO eliminarPartido(Long id) {
        Partido partidoExistente = partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con ID: " + id));

        partidoRepository.delete(partidoExistente);

        return convertToDTO(partidoExistente);
    }

    private PartidoDTO convertToDTO(Partido partido) {
        return PartidoDTO.builder()
                .idPartido(partido.getIdPartido())
                .nombrePartido(partido.getNombrePartido())
                .sigla(partido.getSigla())
                .lema(partido.getLema())
                .build();
    }

    private Partido convertToEntity(PartidoDTO partidoDTO) {
        return Partido.builder()
                .idPartido(partidoDTO.getIdPartido())
                .nombrePartido(partidoDTO.getNombrePartido())
                .sigla(partidoDTO.getSigla())
                .lema(partidoDTO.getLema())
                .build();
    }

    @Override
    @Transactional
    public void eliminarPartidoFisicamente(Long id) {
        Partido partidoExistente = partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con ID: " + id));
        partidoRepository.delete(partidoExistente);
    }

  
}
