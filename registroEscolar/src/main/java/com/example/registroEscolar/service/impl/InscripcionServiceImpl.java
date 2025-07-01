package com.example.registroEscolar.service.impl;

import com.example.registroEscolar.dto.InscripcionDTO;
import com.example.registroEscolar.exception.ResourceNotFoundException;
import com.example.registroEscolar.mapper.InscripcionMapper;
import com.example.registroEscolar.model.Curso;
import com.example.registroEscolar.model.Estudiante;
import com.example.registroEscolar.model.Inscripcion;
import com.example.registroEscolar.repository.CursoRepository;
import com.example.registroEscolar.repository.EstudianteRepository;
import com.example.registroEscolar.repository.InscripcionRepository;
import com.example.registroEscolar.service.InscripcionService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;
    private final InscripcionMapper inscripcionMapper;

    public InscripcionServiceImpl(InscripcionRepository inscripcionRepository,
                                   EstudianteRepository estudianteRepository,
                                   CursoRepository cursoRepository,
                                   InscripcionMapper inscripcionMapper) {
        this.inscripcionRepository = inscripcionRepository;
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
        this.inscripcionMapper = inscripcionMapper;
    }

    @Override //Lógica del método para crear una inscripción
    public InscripcionDTO registrar(InscripcionDTO dto) {
        if (inscripcionRepository.existsByEstudiante_IdPersonaAndCurso_IdCurso(dto.getIdEstudiante(), dto.getIdCurso())) {
            throw new RuntimeException("El estudiante ya está inscrito en este curso.");
        }

        Estudiante estudiante = estudianteRepository.findById(dto.getIdEstudiante())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante con ID " + dto.getIdEstudiante() + " no fue encontrado"));

        Curso curso = cursoRepository.findById(dto.getIdCurso())
                .orElseThrow(() -> new ResourceNotFoundException("El curso con ID " + dto.getIdCurso() + " no fue encontrado"));

        Inscripcion inscripcion = inscripcionMapper.toEntity(dto, estudiante, curso);
        Inscripcion guardada = inscripcionRepository.save(inscripcion);

        return inscripcionMapper.toDTO(guardada);
    }

    @Override //Lógica del método para listar todas las insripciones
    public List<InscripcionDTO> listar() {
        return inscripcionRepository.findAll()
                .stream()
                .map(inscripcionMapper::toDTO)
                .toList();
    }

    @Override //Lógica del método para obtener una inscripción por ID
    public InscripcionDTO obtenerPorId(Integer id) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada con ID: " + id));
        return inscripcionMapper.toDTO(inscripcion);
    }

    @Override //Lógica del método para eliminar una inscripción por ID
    public void eliminar(Integer id) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada con ID: " + id));
        inscripcionRepository.delete(inscripcion);
    }

    @Override //Lógica del método para actualizar unicamente la fecha de inscripción mediante ID
    public InscripcionDTO actualizarFechaInscripcion(Integer id, LocalDate nuevaFecha) {
        if (nuevaFecha.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inscripción no puede ser en el futuro.");
        }
        Inscripcion inscripcion = inscripcionRepository.findById(id)
        
        .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada con ID: " + id));
        
        inscripcion.setFechaInscripcion(nuevaFecha);
        Inscripcion actualizada = inscripcionRepository.save(inscripcion);
        
        return inscripcionMapper.toDTO(actualizada);
    }

    // Lógica para los filtros
    @Override
    public List<InscripcionDTO> filtrarInscripciones(String nombreEstudiante, String apellidoEstudiante, String nombreCurso, Integer anoInscripcion) {
        return inscripcionRepository.filtrarInscripciones(nombreEstudiante, apellidoEstudiante, nombreCurso, anoInscripcion)
                .stream()
                .map(inscripcionMapper::toDTO)
                .toList();
        }
}
