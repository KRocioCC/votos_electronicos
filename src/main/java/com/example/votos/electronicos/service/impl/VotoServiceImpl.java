package com.example.votos.electronicos.service.impl;

import com.example.votos.electronicos.model.Voto;
import com.example.votos.electronicos.dto.CarreraVotoDTO;
import com.example.votos.electronicos.dto.ConteoVotosPartidoDTO;
import com.example.votos.electronicos.dto.VotoDTO;
import com.example.votos.electronicos.model.Partido;
import com.example.votos.electronicos.model.Votante;
import com.example.votos.electronicos.repository.VotoRepository;
import com.example.votos.electronicos.repository.PartidoRepository;
import com.example.votos.electronicos.repository.VotanteRepository;
import com.example.votos.electronicos.service.IVotoService;
import com.example.votos.electronicos.validation.VotoValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VotoServiceImpl implements IVotoService {

    private final VotoRepository votoRepository;
    private final VotanteRepository votanteRepository;
    private final PartidoRepository partidoRepository;
    private final VotoValidator votoValidator;

    @Autowired
    public VotoServiceImpl(
            VotoRepository votoRepository,
            VotanteRepository votanteRepository,
            PartidoRepository partidoRepository,
            VotoValidator votoValidator) {
        this.votoRepository = votoRepository;
        this.votanteRepository = votanteRepository;
        this.partidoRepository = partidoRepository;
        this.votoValidator = votoValidator;
    }

    @Override
    public List<VotoDTO> obtenerTodosLosVotos() {
        return votoRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public VotoDTO obtenerVotoPorId(Long id) {
        Voto voto = votoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voto no encontrado con ID: " + id));
        return convertToDTO(voto);
    }

    @Override
    @Transactional
    public VotoDTO crearVoto(VotoDTO votoDTO) {
        votoValidator.validacionCompletaVoto(votoDTO);

        Votante votante = votanteRepository.findById(votoDTO.getIdVotante())
                .orElseThrow(() -> new RuntimeException("Votante no encontrado"));

        Partido partido = partidoRepository.findById(votoDTO.getIdPartido())
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));

        Voto voto = Voto.builder()
                .votante(votante)
                .partido(partido)
                .build();

        Voto votoGuardado = votoRepository.save(voto);

        if (!Boolean.TRUE.equals(votante.getVoto())) {
            votante.setVoto(true);
            votanteRepository.save(votante);
        }

        return convertToDTO(votoGuardado);
    }

    @Override
    @Transactional
    public VotoDTO actualizarVoto(Long id, VotoDTO votoDTO) {
        Voto votoExistente = votoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voto no encontrado con ID: " + id));

        votoValidator.validacionCompletaVoto(votoDTO);

        Votante votante = votanteRepository.findById(votoDTO.getIdVotante())
                .orElseThrow(() -> new RuntimeException("Votante no encontrado"));

        Partido partido = partidoRepository.findById(votoDTO.getIdPartido())
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));

        votoExistente.setVotante(votante);
        votoExistente.setPartido(partido);

        Voto votoActualizado = votoRepository.save(votoExistente);

        if (!Boolean.TRUE.equals(votante.getVoto())) {
            votante.setVoto(true);
            votanteRepository.save(votante);
        }

        return convertToDTO(votoActualizado);
    }

    @Override
    @Transactional
    public VotoDTO eliminarVoto(Long id) {
        Voto votoExistente = votoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voto no encontrado con ID: " + id));

        votoRepository.delete(votoExistente);
        return convertToDTO(votoExistente);
    }

    @Override
    @Transactional
    public void eliminarVotoFisicamente(Long id) {
        Voto votoExistente = votoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voto no encontrado con ID: " + id));
        votoRepository.delete(votoExistente);
    }

    private VotoDTO convertToDTO(Voto voto) {
        return VotoDTO.builder()
                .idVoto(voto.getIdVoto())
                .idVotante(voto.getVotante().getId())
                .idPartido(voto.getPartido().getIdPartido())
                .build();
    }
    public List<ConteoVotosPartidoDTO> contarVotosPorPartido() {
    List<Object[]> rows = votoRepository.contarVotosPorPartidoRaw();
    return rows.stream()
        .map(r -> new ConteoVotosPartidoDTO(
            (String) r[0],
            ((Number) r[1]).longValue()
        ))
        .collect(Collectors.toList());
        }
        
        @Override
        public List<CarreraVotoDTO> contarVotosPorCarrera() {
        List<Object[]> rows = votoRepository.contarVotosPorCarreraRaw();
        return rows.stream()
                .map(r -> new CarreraVotoDTO(
                (String) r[0],
                ((Number) r[1]).longValue()
                ))
                .collect(Collectors.toList());
        }


}
