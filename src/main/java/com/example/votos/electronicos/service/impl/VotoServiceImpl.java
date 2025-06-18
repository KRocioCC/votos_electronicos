package com.example.votos.electronicos.service.impl;

import com.example.votos.electronicos.model.Voto;
import com.example.votos.electronicos.dto.VotoDTO;
import com.example.votos.electronicos.model.Candidato;
import com.example.votos.electronicos.model.Estudiante;
import com.example.votos.electronicos.model.Docente;
import com.example.votos.electronicos.model.Partido;
import com.example.votos.electronicos.repository.VotoRepository;
import com.example.votos.electronicos.repository.EstudianteRepository;
import com.example.votos.electronicos.repository.DocenteRepository;
import com.example.votos.electronicos.repository.PartidoRepository;
import com.example.votos.electronicos.repository.CandidatoRepository;
import com.example.votos.electronicos.service.IVotoService;
import com.example.votos.electronicos.validation.VotoValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import com.example.votos.electronicos.dto.ConteoVotosCandidatoDTO;
import com.example.votos.electronicos.dto.ConteoVotosPartidoCandidatoDTO;

@Service
public class VotoServiceImpl implements IVotoService {

    private final VotoRepository votoRepository;
    private final EstudianteRepository estudianteRepository;
    private final DocenteRepository docenteRepository;
    private final PartidoRepository partidoRepository;
    private final CandidatoRepository candidatoRepository;
    private final VotoValidator votoValidator;

    @Autowired
    public VotoServiceImpl(
            VotoRepository votoRepository,
            EstudianteRepository estudianteRepository,
            DocenteRepository docenteRepository,
            PartidoRepository partidoRepository,
            CandidatoRepository candidatoRepository,
            VotoValidator votoValidator) {
        this.votoRepository = votoRepository;
        this.estudianteRepository = estudianteRepository;
        this.docenteRepository = docenteRepository;
        this.partidoRepository = partidoRepository;
        this.candidatoRepository = candidatoRepository;
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

        Estudiante estudiante = null;
        if (votoDTO.getIdEstudiante() != null) {
            estudiante = estudianteRepository.findById(votoDTO.getIdEstudiante())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        }
        Docente docente = null;
        if (votoDTO.getIdDocente() != null) {
            docente = docenteRepository.findById(votoDTO.getIdDocente())
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
        }
        Partido partido = null;
        if (votoDTO.getIdPartido() != null) {
            partido = partidoRepository.findById(votoDTO.getIdPartido())
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));
        }
        Candidato candidato = null;
        if (votoDTO.getIdCandidato() != null) {
            candidato = candidatoRepository.findById(votoDTO.getIdCandidato())
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));
        }

        Voto voto = Voto.builder()
                .estudiante(estudiante)
                .docente(docente)
                .partido(partido)
                .candidato(candidato)
                .build();

        Voto votoGuardado = votoRepository.save(voto);

        // Cambiar el atributo 'voto' a true
        if (estudiante != null && !Boolean.TRUE.equals(estudiante.getVoto())) {
            estudiante.setVoto(true);
            estudianteRepository.save(estudiante);
        }
        if (docente != null && !Boolean.TRUE.equals(docente.getVoto())) {
            docente.setVoto(true);
            docenteRepository.save(docente);
        }

        return convertToDTO(votoGuardado);
    }

    @Override
    @Transactional
    public VotoDTO actualizarVoto(Long id, VotoDTO votoDTO) {
        Voto votoExistente = votoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voto no encontrado con ID: " + id));

        votoValidator.validacionCompletaVoto(votoDTO);

        Estudiante estudiante = null;
        if (votoDTO.getIdEstudiante() != null) {
            estudiante = estudianteRepository.findById(votoDTO.getIdEstudiante())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        }
        Docente docente = null;
        if (votoDTO.getIdDocente() != null) {
            docente = docenteRepository.findById(votoDTO.getIdDocente())
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
        }
        Partido partido = null;
        if (votoDTO.getIdPartido() != null) {
            partido = partidoRepository.findById(votoDTO.getIdPartido())
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));
        }
        Candidato candidato = null;
        if (votoDTO.getIdCandidato() != null) {
            candidato = candidatoRepository.findById(votoDTO.getIdCandidato())
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));
        }

        votoExistente.setEstudiante(estudiante);
        votoExistente.setDocente(docente);
        votoExistente.setPartido(partido);
        votoExistente.setCandidato(candidato);

        Voto votoActualizado = votoRepository.save(votoExistente);

        // Cambiar el atributo 'voto' a true si corresponde
        if (estudiante != null && !Boolean.TRUE.equals(estudiante.getVoto())) {
            estudiante.setVoto(true);
            estudianteRepository.save(estudiante);
        }
        if (docente != null && !Boolean.TRUE.equals(docente.getVoto())) {
            docente.setVoto(true);
            docenteRepository.save(docente);
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
        VotoDTO dto = new VotoDTO();
        dto.setIdVoto(voto.getIdVoto());

        if (voto.getEstudiante() != null) {
            dto.setIdEstudiante(voto.getEstudiante().getIdEstudiante());
        } else {
            dto.setIdEstudiante(null);
        }

        if (voto.getDocente() != null) {
            dto.setIdDocente(voto.getDocente().getIdDocente());
        } else {
            dto.setIdDocente(null);
        }

        if (voto.getPartido() != null) {
            dto.setIdPartido(voto.getPartido().getIdPartido());
        } else {
            dto.setIdPartido(null);
        }

        if (voto.getCandidato() != null) {
            dto.setIdCandidato(voto.getCandidato().getIdCandidato());
        } else {
            dto.setIdCandidato(null);
        }

        return dto;
    }

    private Voto convertToEntity(VotoDTO votoDTO) {
        Estudiante estudiante = null;
        if (votoDTO.getIdEstudiante() != null) {
            estudiante = estudianteRepository.findById(votoDTO.getIdEstudiante())
                .orElse(null);
        }
        Docente docente = null;
        if (votoDTO.getIdDocente() != null) {
            docente = docenteRepository.findById(votoDTO.getIdDocente())
                .orElse(null);
        }
        Partido partido = null;
        if (votoDTO.getIdPartido() != null) {
            partido = partidoRepository.findById(votoDTO.getIdPartido())
                .orElse(null);
        }
        Candidato candidato = null;
        if (votoDTO.getIdCandidato() != null) {
            candidato = candidatoRepository.findById(votoDTO.getIdCandidato())
                .orElse(null);
        }

        return Voto.builder()
                .idVoto(votoDTO.getIdVoto())
                .estudiante(estudiante)
                .docente(docente)
                .partido(partido)
                .candidato(candidato)
                .build();
    }

    private Candidato createCandidatoFromId(Long idCandidato) {
        return idCandidato != null ? new Candidato(idCandidato) : null;
    }

    // Para la función de conteo de votos por candidato
    public List<ConteoVotosCandidatoDTO> contarVotosPorCandidato() {
        List<Object[]> rows = votoRepository.contarVotosPorCandidatoRaw();
        return rows.stream()
            .map(r -> new ConteoVotosCandidatoDTO(
                ((Number) r[0]).longValue(),
                (String) r[1],
                ((Number) r[2]).longValue()
            ))
            .collect(java.util.stream.Collectors.toList());
    }

    // Para la función de conteo de votos por partido y candidato
    public List<ConteoVotosPartidoCandidatoDTO> contarVotosPorPartidoYCandidato() {
        List<Object[]> rows = votoRepository.contarVotosPorPartidoYCandidatoRaw();
        return rows.stream()
            .map(r -> new ConteoVotosPartidoCandidatoDTO(
                (String) r[0],
                (String) r[1],
                ((Number) r[2]).longValue()
            ))
            .collect(java.util.stream.Collectors.toList());
    }
}